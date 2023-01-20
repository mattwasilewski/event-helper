package com.codecool.CodeCoolProjectGrande.event.service;


import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.event_provider.global_model.GlobalEvent;
import com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model.WroclawEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public interface EventService {

    List<Event> getEvents();

    Optional<Event> getEventByID(UUID eventID);

    Optional<Event> createEvent(Event event);

    List<Event> findEventsByEventType(EventType eventType, int page, int size);

    List<Event> sortEvents(String sortBy, boolean ascending, String phrase, int page, int size);

    Optional<Event> assignUserToEvent(Map data);

    Optional<Event> editEventDescriptionByEventId(Map data);

    Event serializeWroclawData(WroclawEvent event);
    Event serializeGlobalData(GlobalEvent event);
    List<String> saveGlobalData();
    List<String> saveWroclawData();

    List<Event> getAssignedEvents(String email);

    boolean isUserAssignToEvent(UUID eventId, String userEmail);


}
