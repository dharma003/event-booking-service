
package com.org.event.book.controller;

import com.org.event.book.entity.Event;
import com.org.event.book.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/events")
    public List<Event> getEvents(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String category) {
        // JWT validation normally done via Spring Security filter
        return eventService.getEventsByCategory(category);
    }

    @PostMapping("/onboardEvents")
    public ResponseEntity<?> onboardingEvents(@RequestBody List<Event> events){
        List<Event> eventsList= eventService.saveEventsByCategory(events);
        return  ResponseEntity.ok(eventsList);
    }
}
