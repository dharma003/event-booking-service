package com.org.event.book.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "booking_seats")
public record BookingSeat(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        Long bookingId,
        Long seatId

) {}
