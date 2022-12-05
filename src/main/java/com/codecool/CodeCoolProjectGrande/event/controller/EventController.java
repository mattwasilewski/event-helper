package com.codecool.CodeCoolProjectGrande.event.controller;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final EventServiceImpl eventService;


    @Autowired
    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public List<Event> getEvents(){
        return eventService.getEvents();
    }

    @GetMapping("{eventID}")
    public Optional<Event> getEventByID(@PathVariable UUID eventID) {
        return eventService.getEventByID(eventID);
    }

    @PostMapping("create-event")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getEventByType/{eventType}")
    public List<Event> getEventsByEventType(@PathVariable EventType eventType){
        return eventService.getEventsByEventType(eventType);
    }

    @GetMapping("/sort/{sortBy}&{ascending}&{phrase}&{page}&{size}")
    public List<Event> sortEvents(@PathVariable String sortBy, @PathVariable boolean ascending, @PathVariable String phrase,
                                  @PathVariable int page, @PathVariable int size) {
        return eventService.sortEvents(sortBy, ascending, phrase, page, size);
    }

    @PutMapping("/assign-user-to-event")
    public ResponseEntity<?> assignUserToEvent(@RequestBody Map data) {
        return eventService.assignUserToEvent(data);
    }

    @PutMapping("/edit-event-description")
    public ResponseEntity<?> editEventDescriptionByEventId(@RequestBody Map data){
        return eventService.editEventDescriptionByEventId(data);
    }

    @GetMapping("data")
    public void saveWroclawData() {
        eventService.saveWroclawData();
    }



}


