package com.codecool.CodeCoolProjectGrande.event.service;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.repository.EventRepository;
import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    public Optional<Event> getEventByID(UUID eventID) {
        return eventRepository.findEventByEventId(eventID);
    }


    public void createEvent(Event event) {
        eventRepository.save(event);
    }


    public List<Event> getEventsByEventType(EventType eventType){
        return eventRepository.findEventsByEventType(eventType);
    }


    public List<Event> sortEvents(String sortBy, boolean ascending, String phrase, int page, int size) {
        if (ascending) {
            return eventRepository.findAllByNameContainingOrDescriptionContaining(phrase, phrase,
                    PageRequest.of(page, size), Sort.by(sortBy).ascending());
        }
        return eventRepository.findAllByNameContainingOrDescriptionContaining(phrase, phrase,
                PageRequest.of(page, size), Sort.by(sortBy).descending());
    }


    public ResponseEntity<?> assignUserToEvent(Map data) {
        Optional<Event> event = eventRepository.findEventByEventId(UUID.fromString(String.valueOf(data.get("eventId"))));
        Optional<User> user = userRepository.findUserByUserId(UUID.fromString(String.valueOf(data.get("userId"))));
        //TODO czy zwracac responseentity/ true/false
        if (event.isPresent() && user.isPresent()) {
            event.get().assignUser(user.get());
            eventRepository.save(event.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<?> editEventDescriptionByEventId(Map data){
        Optional<Event> event = eventRepository.findEventByEventId(UUID.fromString(String.valueOf(data.get("eventId"))));
        System.out.println(data.get("description"));
        if(event.isPresent()){
            event.get().setDescription(String.valueOf(data.get("description")));
            eventRepository.save(event.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
