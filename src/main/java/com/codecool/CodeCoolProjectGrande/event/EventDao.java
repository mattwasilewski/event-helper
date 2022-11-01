package com.codecool.CodeCoolProjectGrande.event;

import com.codecool.CodeCoolProjectGrande.account.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventDao {
    void createEvent(Event event);
    Optional<Event> getEventByID(UUID id);
    List<Event> getAllEvents();
    void deleteEvent(UUID id);
    void editEvent(UUID id, String name, String description, int price);
}
