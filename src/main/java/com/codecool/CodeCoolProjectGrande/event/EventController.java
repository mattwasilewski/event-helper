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

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping()
    public List<Event> getEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("{eventID}")
    public Optional<Event> getEventByID(@PathVariable UUID eventID) {
        return eventService.getEventByID(eventID);
    }

    @PostMapping("create-event")
    public void createUser(@RequestBody Event event) {
        eventService.createEvent(event);
    }

    @GetMapping("/sort/{sortBy}&{ascending}")
    public List<Event> sortEvents(@PathVariable("sortBy") String sortBy, @PathVariable("ascending") boolean ascending) {
        return eventService.sortEvents(sortBy, ascending);
    }

}


