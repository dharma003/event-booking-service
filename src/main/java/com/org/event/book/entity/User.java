package com.org.event.book.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public record User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    String username,
    String password,
    String role

) {}
