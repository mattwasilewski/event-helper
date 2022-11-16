package com.codecool.CodeCoolProjectGrande.event;

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

    private final EventServiceImpl eventDaoImpl;

    @Autowired
    public EventController(EventServiceImpl eventDaoImpl) {
        this.eventDaoImpl = eventDaoImpl;
    }

    @GetMapping()
    public List<Event> getEvents(){
        System.out.println("Get events");
        return eventDaoImpl.getEvents();
    }

    @GetMapping("{eventID}")
    public Optional<Event> getEventByID(@PathVariable UUID eventID) {
        System.out.println("wszedł mi mapping !!!!");
        System.out.println(eventID);
        System.out.println(eventDaoImpl.getEventByID(eventID));
        return eventDaoImpl.getEventByID(eventID);
    }

    @PostMapping("create-event")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        eventDaoImpl.createEvent(event);
        return new ResponseEntity<>("Event added", HttpStatus.OK);
    }


    @GetMapping("/sort/{sortBy}&{ascending}")
    public List<Event> sortEvents(@PathVariable String sortBy, @PathVariable boolean ascending) {
        System.out.println("Dziala :) sort by: " + sortBy + " asc: " + ascending);
        return eventDaoImpl.getSortedEvents(sortBy, ascending);
    }

}


