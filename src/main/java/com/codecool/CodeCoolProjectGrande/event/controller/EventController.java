package com.codecool.CodeCoolProjectGrande.event.controller;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/api/events/")
public class EventController {

    private final EventRepository eventRepository;


    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping()
    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    @GetMapping("{eventID}")
    public Optional<Event> getEventByID(@PathVariable UUID eventID) {
        return eventRepository.findEventByEventId(eventID);
    }

    @PostMapping("create-event")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        eventRepository.save(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getEventByType/{eventType}")
    public List<Event> getEventsByEventType(@PathVariable String eventType){
        return eventRepository.findEventsByEventType(eventType);
    }
    @GetMapping("/sort/{sortBy}&{ascending}")
    public List<Event> sortEvents(@PathVariable String sortBy, @PathVariable boolean ascending) {
        if (ascending) {
            return eventRepository.findAll(Sort.by(sortBy).ascending());
        }
        return eventRepository.findAll(Sort.by(sortBy).descending());
    }


}


