package com.org.event.book.service;

import com.org.event.book.entity.Booking;
import com.org.event.book.entity.Seat;
import com.org.event.book.repository.BookingRepository;
import com.org.event.book.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service


@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;

    @Transactional
    public Booking bookSeat(Long userId,
                            Long eventId,
                            Long seatId,
                            String idempotencyKey) {

        Optional<Booking> existing =
                bookingRepository.findByIdempotencyKey(idempotencyKey);

        if (existing.isPresent()) {
            return existing.get();
        }

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (!"AVAILABLE".equals(seat.status())) {
            throw new RuntimeException("Seat already booked");
        }

        seatRepository.updateStatus(seatId, "BOOKED");

        Booking booking = new Booking(
                null,
                eventId,
                seatId,
                userId,
                idempotencyKey,
                "CONFIRMED"
        );

        return bookingRepository.save(booking);
    }
}

