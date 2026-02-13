package com.org.event.book.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public record Seat(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        Long eventId,

        String seatNumber,

        String status   // AVAILABLE, BOOKED

) {}
