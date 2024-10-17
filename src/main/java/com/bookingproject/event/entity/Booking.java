package com.bookingproject.event.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // The user who made the booking

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;  // The event that is being booked

    @Column(nullable = false)
    private String status;  // Booking status (e.g., CONFIRMED, CANCELLED)

    @Column(nullable = false)
    private String bookingDate;  // Date when the booking was made

    // Constructors
    public Booking() {
    }

    public Booking(int id, User user, Event event, String status, String bookingDate) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.status = status;
        this.bookingDate = bookingDate;
    }
}
