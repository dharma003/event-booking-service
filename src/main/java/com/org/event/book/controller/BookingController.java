package com.org.event.book.controller;

import com.org.event.book.entity.Booking;
import com.org.event.book.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking bookTicket(

            @RequestHeader("Authorization") String authHeader,
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @RequestParam Long userId,
            @RequestParam Long eventId,
            @RequestParam Long seatId) {

        return bookingService.bookSeat(
                userId,
                eventId,
                seatId,
                idempotencyKey);
    }
}
