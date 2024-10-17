package com.bookingproject.event.service;


import com.bookingproject.event.entity.Booking;
import com.bookingproject.event.entity.Event;
import com.bookingproject.event.entity.User;
import com.bookingproject.event.repository.BookingRepository;
import com.bookingproject.event.repository.EventRepository;
import com.bookingproject.event.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    // Create a new booking
    public Booking createBooking(Booking booking) {
        Optional<User> userOptional = userRepository.findById(booking.getUser().getId());
        Optional<Event> eventOptional = eventRepository.findById(booking.getEvent().getId());

        if (userOptional.isPresent() && eventOptional.isPresent()) {
            booking.setUser(userOptional.get());
            booking.setEvent(eventOptional.get());
            booking.setStatus("PENDING"); // Set initial status
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("User or Event not found.");
        }
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get a booking by ID
    public Booking getBookingById(int bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found."));
    }

    // Get bookings by User ID
    public List<Booking> getBookingsByUserId(int userId) {
        if (userRepository.existsById(userId)) {
            return bookingRepository.findByUserId(userId);
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    // Get bookings by Event ID
    public List<Booking> getBookingsByEventId(int eventId) {
        if (eventRepository.existsById(eventId)) {
            return bookingRepository.findByEventId(eventId);
        } else {
            throw new RuntimeException("Event not found.");
        }
    }

    // Update booking status and other booking details
    public Booking updateBooking(int bookingId, Booking updatedBooking) {
        Optional<Booking> existingBookingOptional = bookingRepository.findById(bookingId);

        if (existingBookingOptional.isPresent()) {
            Booking existingBooking = existingBookingOptional.get();
            existingBooking.setStatus(updatedBooking.getStatus());
            // Update other fields if needed, like booking date, etc.
            return bookingRepository.save(existingBooking);
        } else {
            throw new RuntimeException("Booking not found.");
        }
    }

    // Delete a booking by ID
    public void deleteBooking(int bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
        } else {
            throw new RuntimeException("Booking not found.");
        }
    }
}
