package com.org.event.book.service;

import com.org.event.book.entity.Event;
import com.org.event.book.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getEventsByCategory(String category) {
        return eventRepository.findByCategory(category);
    }

    public List<Event> saveEventsByCategory( List<Event> events) {
        return eventRepository.save(events);
    }
}
