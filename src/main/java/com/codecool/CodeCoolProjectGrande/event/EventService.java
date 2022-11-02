package com.codecool.CodeCoolProjectGrande.event;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService implements EventDao {


    @Override
    public void createEvent(Event event) {
        EventRepository.EVENTS_IN_MEMORY.add(event);
    }

    @Override
    public Optional<Event> getEventByID(UUID id) {
        return EventRepository.EVENTS_IN_MEMORY.stream().filter(event -> event.getEventID() == id).findFirst();
    }

    @Override
    public List<Event> getAllEvents() {
        return EventRepository.EVENTS_IN_MEMORY;
    }

    @Override
    public  List<Event> sortEvents(String sortBy) {    // TODO dokończyć sortowanie po dacie
        switch (sortBy) {
            case "name" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparing(Event::getName)).toList(); }
            case "price" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparingInt(Event::getPrice)).toList(); }
            case "date" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparing(Event::getDate, Comparator.nullsLast(Comparator.reverseOrder()))).toList(); }
            case "eventType" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparing(Event::getEventType)).toList(); }
            default -> { return EventRepository.EVENTS_IN_MEMORY; }
        }
    }

    @Override
    public void deleteEvent(UUID id) {
        if (getEventByID(id).isPresent()){
            EventRepository.EVENTS_IN_MEMORY.remove(getEventByID(id).get());
        } else {
            System.out.println("Place for logger");
        }
    }

    @Override
    public void editEvent(UUID id, String name, String description, int price) {
        if (getEventByID(id).isPresent()) {
            getEventByID(id).get().setName(name);
            getEventByID(id).get().setDescription(description);
            getEventByID(id).get().setPrice(price);
        } else {
            System.out.println("Place for logger");
        }
    }
}
