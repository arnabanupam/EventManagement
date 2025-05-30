package com.shopdeck.eventmanagement.services;

import com.shopdeck.eventmanagement.models.Event;
import com.shopdeck.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private EventRepository eventRepository;

    public String registerUser(String eventId, String userId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
//        if (event.getRegistrations().size() >= event.getCapacity()) {
//            throw new RuntimeException("Event capacity full");
//        }
        //event.getRegistrations().add(userId);
        eventRepository.save(event);
        return "Registered successfully";
    }
}

