package com.org.event.book;

import com.org.event.book.entity.Booking;
import com.org.event.book.entity.Seat;
import com.org.event.book.repository.BookingRepository;
import com.org.event.book.repository.SeatRepository;
import com.org.event.book.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ SUCCESS CASE
    @Test
    void shouldBookSeatSuccessfully() {

        Long seatId = 1L;
        String idempotencyKey = "abc";

        Seat seat = new Seat(seatId, 100L, "A1", "AVAILABLE");

        when(bookingRepository.findByIdempotencyKey(idempotencyKey))
                .thenReturn(Optional.empty());

        when(seatRepository.findById(seatId))
                .thenReturn(Optional.of(seat));

        Booking savedBooking = new Booking(
                1L, 200L, seatId, 10L, idempotencyKey, "CONFIRMED"
        );

        when(bookingRepository.save(any()))
                .thenReturn(savedBooking);

        Booking result = bookingService.bookSeat(
                10L, 200L, seatId, idempotencyKey);

        assertNotNull(result);
        assertEquals("CONFIRMED", result.status());

        verify(seatRepository).updateStatus(seatId, "BOOKED");
        verify(bookingRepository).save(any());
    }

    // ✅ IDEMPOTENCY CASE
    @Test
    void shouldReturnExistingBookingIfIdempotencyKeyExists() {

        String key = "existing";

        Booking existing = new Booking(
                1L, 200L, 1L, 10L, key, "CONFIRMED"
        );

        when(bookingRepository.findByIdempotencyKey(key))
                .thenReturn(Optional.of(existing));

        Booking result = bookingService.bookSeat(
                10L, 200L, 1L, key);

        assertEquals(existing, result);

        verify(seatRepository, never()).findById(any());
        verify(bookingRepository, never()).save(any());
    }

    // ✅ SEAT NOT FOUND
    @Test
    void shouldThrowExceptionWhenSeatNotFound() {

        when(bookingRepository.findByIdempotencyKey(any()))
                .thenReturn(Optional.empty());

        when(seatRepository.findById(any()))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> bookingService.bookSeat(1L, 1L, 1L, "key"));

        assertEquals("Seat not found", ex.getMessage());
    }

    // ✅ SEAT ALREADY BOOKED
    @Test
    void shouldThrowExceptionWhenSeatAlreadyBooked() {

        Seat seat = new Seat(1L, 1L, "A1", "BOOKED");

        when(bookingRepository.findByIdempotencyKey(any()))
                .thenReturn(Optional.empty());

        when(seatRepository.findById(any()))
                .thenReturn(Optional.of(seat));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> bookingService.bookSeat(1L, 1L, 1L, "key"));

        assertEquals("Seat already booked", ex.getMessage());
    }
}
