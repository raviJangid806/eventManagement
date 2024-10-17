package com.bookingproject.event.service;


import com.bookingproject.event.entity.Event;
import com.bookingproject.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Create a new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get an event by ID
    public Event getEventById(int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found."));
    }

    // Update an existing event
    public Event updateEvent(int eventId, Event updatedEvent) {
        Optional<Event> existingEventOptional = eventRepository.findById(eventId);

        if (existingEventOptional.isPresent()) {
            Event existingEvent = existingEventOptional.get();
            existingEvent.setTitle(updatedEvent.getTitle());
            existingEvent.setDescription(updatedEvent.getDescription());
            existingEvent.setDateTime(updatedEvent.getDateTime());
            existingEvent.setLocation(updatedEvent.getLocation());
            // Update any other fields as necessary

            return eventRepository.save(existingEvent);
        } else {
            throw new RuntimeException("Event not found.");
        }
    }

    // Delete an event by ID
    public void deleteEvent(int eventId) {
        if (eventRepository.existsById(eventId)) {
            eventRepository.deleteById(eventId);
        } else {
            throw new RuntimeException("Event not found.");
        }
    }
}
