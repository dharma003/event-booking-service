package com.org.event.book.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public record Booking(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        Long eventId,
        Long seatId,
        Long userId,
        String idempotencyKey,
        String status

) {}

