package com.org.event.book.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public record Event(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    String name,
    String category,   // Dance, Play, Music etc
    LocalDateTime eventDate

) {}
