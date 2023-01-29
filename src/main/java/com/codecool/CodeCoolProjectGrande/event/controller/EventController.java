package com.codecool.CodeCoolProjectGrande.event.controller;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.service.EventServiceImpl;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;


@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/api/events/")
public class EventController {

    private final EventServiceImpl eventService;


    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }


    @GetMapping()
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("{eventID}")
    public Optional<Event> getEventByID(@PathVariable UUID eventID) {
        return eventService.getEventByID(eventID);
    }

    @PostMapping("create-event")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/getEventByType/{eventType}&{page}&{size}")
    public List<Event> getEventsByEventType(@PathVariable EventType eventType, @PathVariable int page,
                                            @PathVariable int size) {
        return eventService.findEventsByEventType(eventType, page, size);

    }

    @GetMapping("/sort/{sortBy}&{ascending}&{phrase}&{page}&{size}")
    public List<Event> sortEvents(@PathVariable String sortBy, @PathVariable boolean ascending, @PathVariable String phrase,
                                  @PathVariable int page, @PathVariable int size) {
        return eventService.sortEvents(sortBy, ascending, phrase, page, size);
    }

    @GetMapping("/is-assign/{id}&{userEmail}")
    public boolean isAssignToEvent(@PathVariable UUID id, @PathVariable String userEmail) {
        return eventService.isUserAssignToEvent(id, userEmail);
    }

    @PutMapping("/assign-user-to-event")
    public ResponseEntity<?> assignUserToEvent(@RequestBody Map data) {
        Optional<Event> event = eventService.assignUserToEvent(data);
        if (event.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/edit-event-description")
    public ResponseEntity<?> editEventDescriptionByEventId(@RequestBody Map data) {
        Optional<Event> event = eventService.editEventDescriptionByEventId(data);
        if (event.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("data")
    public ResponseEntity<?> saveWroclawData() {
        List<String> saveWroclawData = eventService.saveWroclawData();
        if (saveWroclawData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @GetMapping("global-data")
    public ResponseEntity<?> saveGlobalData() {
        List<String> saveGlobalEvents = eventService.saveGlobalData();
        if (saveGlobalEvents == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


    @GetMapping("assign-to-user/{email}&{page}&{size}")
    public List<Event> getAssignedEvents(@PathVariable String email, @PathVariable int page, @PathVariable int size) {
        return eventService.getAssignedEvents(email);
    }


}


