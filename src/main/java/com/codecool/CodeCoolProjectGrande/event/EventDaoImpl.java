package com.codecool.CodeCoolProjectGrande.event;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventDaoImpl implements EventDao {


    @Override
    public void createEvent(Event event) {
        EventRepository.EVENTS_IN_MEMORY.add(event);
    }

    @Override
    public Optional<Event> getEventByID(UUID id) {
        return EventRepository.EVENTS_IN_MEMORY.stream().filter(event -> event.getEventId().equals(id)).findFirst();
    }

    @Override
    public List<Event> getAllEvents() {
        return EventRepository.EVENTS_IN_MEMORY;
    }

    @Override
    public  List<Event> getSortedEvents(String sortBy, boolean ascending) {
        if (ascending) {
            switch (sortBy) {
                case "name" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparing(Event::getName)).toList(); }
                case "price" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparingInt(Event::getPrice)).toList(); }
                case "date" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparing(Event::getDate)).toList(); }
                case "eventType" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparing(Event::getEventType)).toList(); }
                default -> { return EventRepository.EVENTS_IN_MEMORY; }
            }
        } else {
            switch (sortBy) {
                case "name" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparing(Event::getName).reversed()).toList(); }
                case "price" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparingInt(Event::getPrice).reversed()).toList(); }
                case "date" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparing(Event::getDate).reversed()).toList(); }
                case "eventType" -> { return EventRepository.EVENTS_IN_MEMORY.stream().sorted(Comparator.comparing(Event::getEventType).reversed()).toList(); }
                default -> { return EventRepository.EVENTS_IN_MEMORY; }
            }
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
