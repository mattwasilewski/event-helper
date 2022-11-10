package com.codecool.CodeCoolProjectGrande.event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventService {
    void createEvent(Event event);
    Optional<Event> getEventByID(UUID id);
    List<Event> getEvents();
    List<Event> getSortedEvents(String sortBy, boolean ascending);
    void deleteEvent(UUID id);
    void editEvent(UUID id, String name, String description, int price);

}
