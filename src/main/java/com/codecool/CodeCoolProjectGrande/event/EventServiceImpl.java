package com.codecool.CodeCoolProjectGrande.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Optional<Event> getEventByID(UUID id) {
        return eventRepository.findEventByEventId(id);
    }

    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public  List<Event> getSortedEvents(String sortBy, boolean ascending) {
        if (ascending) {
            return eventRepository.findAll(Sort.by(sortBy).ascending());
        }
        return eventRepository.findAll(Sort.by(sortBy).descending());
    }

    @Override
    public void deleteEvent(UUID id) {
        if (getEventByID(id).isPresent()){
            eventRepository.delete(getEventByID(id).get());
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
