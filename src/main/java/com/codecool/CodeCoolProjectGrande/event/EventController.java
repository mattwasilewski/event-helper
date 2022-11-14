package com.codecool.CodeCoolProjectGrande.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/api/events/")
public class EventController {

    private final EventDaoImpl eventDaoImpl;

    @Autowired
    public EventController(EventDaoImpl eventDaoImpl) {
        this.eventDaoImpl = eventDaoImpl;
    }

    @GetMapping()
    public List<Event> getEvents(){
        return eventDaoImpl.getAllEvents();
    }

    @GetMapping("{eventID}")
    public Optional<Event> getEventByID(@PathVariable UUID eventID) {
        return eventDaoImpl.getEventByID(eventID);
    }

    @PostMapping("create-event")
    public void createUser(@RequestBody Event event) {
        eventDaoImpl.createEvent(event);
    }

    @GetMapping("/sort/{sortBy}&{ascending}")
    public List<Event> sortEvents(@PathVariable("sortBy") String sortBy, @PathVariable("ascending") boolean ascending) {
        return eventDaoImpl.getSortedEvents(sortBy, ascending);
    }

}


