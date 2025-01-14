package com.finance.backend_api.services;

import com.finance.backend_api.models.Event;
import com.finance.backend_api.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public String addEvent(Long userId, String eventType, Map<String, String> details) {
        Event savedEvent = new Event();
        //set event properties
        savedEvent.setUser_id(userId);
        savedEvent.setEvent_type(eventType);
        savedEvent.setTimestamp(new Date());
        savedEvent.setDetails(details);
        //save event
        repository.save(savedEvent);
        return "Event saved successfully";
    }
}
