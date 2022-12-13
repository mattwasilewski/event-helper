package com.codecool.CodeCoolProjectGrande.event.service;


import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.user.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface EventService {

    List<Event> getEvents();

    Optional<Event> getEventByID(UUID eventID);

    void createEvent(Event event);

    List<Event> findEventsByEventType(EventType eventType);

    List<Event> sortEvents(String sortBy, boolean ascending, String phrase, int page, int size);

    ResponseEntity<?> assignUserToEvent(Map data);

    ResponseEntity<?> editEventDescriptionByEventId(Map data);

//    List<Event> getAssigned(UUID userId);


}
