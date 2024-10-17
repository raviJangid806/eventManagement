package com.bookingproject.event.controller;


import com.bookingproject.event.entity.Booking;
import com.bookingproject.event.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {


    @Autowired
    private BookingService bookingService;

    // Create a new booking
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(createdBooking);
    }

    // Get all bookings
    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // Get booking by ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    // Get bookings by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable int userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    // Get bookings by Event ID
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Booking>> getBookingsByEventId(@PathVariable int eventId) {
        List<Booking> bookings = bookingService.getBookingsByEventId(eventId);
        return ResponseEntity.ok(bookings);
    }

    // Update booking status
    @PutMapping("/update/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int id, @RequestBody Booking booking) {
        Booking updatedBooking = bookingService.updateBooking(id, booking);
        return ResponseEntity.ok(updatedBooking);
    }

    // Delete a booking by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}

