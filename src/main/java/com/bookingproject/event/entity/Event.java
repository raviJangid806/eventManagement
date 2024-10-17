package com.bookingproject.event.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private int availableSeats;

    // Constructors
    public Event() {}

    public Event(int id, String title, String description, LocalDateTime dateTime, String location, int availableSeats) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.availableSeats = availableSeats;
    }

// Getters and Setters
    // ...
}
