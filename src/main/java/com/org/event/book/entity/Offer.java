package com.org.event.book.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "offers")
public record Offer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    Long eventId,
    String description,
    Double discount

) {}
