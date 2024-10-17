package com.bookingproject.event.controller;


import com.bookingproject.event.entity.Event;
import com.bookingproject.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Create a new event
    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    // Get all events
    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Get an event by ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    // Update an event by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    // Delete an event by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
