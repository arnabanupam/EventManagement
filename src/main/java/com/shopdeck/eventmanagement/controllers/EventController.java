package com.shopdeck.eventmanagement.controllers;

import com.shopdeck.eventmanagement.models.Event;
import com.shopdeck.eventmanagement.models.User;
import com.shopdeck.eventmanagement.repository.EventRepository;
import com.shopdeck.eventmanagement.repository.UserRepository;
import com.shopdeck.eventmanagement.security.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventRepository eventRepo;
    @Autowired private UserRepository userRepo;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Event event) {
        return ResponseEntity.ok(eventRepo.save(event));
    }

    @GetMapping
    public List<Event> getAll() {
        return eventRepo.findAll();
    }

    @PostMapping("/{id}/register")
    public ResponseEntity<?> register(@PathVariable String id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        Event event = eventRepo.findById(id).orElseThrow();

        if (event.getParticipantIds().size() >= event.getCapacity())
            return ResponseEntity.badRequest().body("Event full");
        if (!user.getRegisteredEventIds().add(id))
            return ResponseEntity.badRequest().body("Already registered");

        event.getParticipantIds().add(user.getId());
        userRepo.save(user);
        eventRepo.save(event);
        return ResponseEntity.ok("Registered");
    }

    @DeleteMapping("/{id}/register")
    public ResponseEntity<?> unregister(@PathVariable String id, HttpServletRequest req) {
        String email = (String) req.getAttribute("userEmail");
        User user = userRepo.findByEmail(email).orElseThrow();
        Event event = eventRepo.findById(id).orElseThrow();

        if (!user.getRegisteredEventIds().remove(id))
            return ResponseEntity.badRequest().body("Not registered for this event");

        event.getParticipantIds().remove(user.getId());
        userRepo.save(user);
        eventRepo.save(event);
        return ResponseEntity.ok("Unregistered");
    }

}

