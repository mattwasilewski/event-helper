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

    private final EventServiceImpl eventDaoImpl;

    @Autowired
    public EventController(EventServiceImpl eventDaoImpl) {
        this.eventDaoImpl = eventDaoImpl;
    }

    @GetMapping()
    public List<Event> getEvents(){
        return eventDaoImpl.getEvents();
    }

    @GetMapping("{eventID}")
    public Optional<Event> getEventByID(@PathVariable UUID eventID) {
        return eventDaoImpl.getEventByID(eventID);
    }

    @PostMapping("create-event")
    public void createEvent(@RequestParam("name") String name,
                            @RequestParam("description") String description,
                            @RequestParam("logo") String logo,
                            @RequestParam("event-type") EventType eventType) {
        eventDaoImpl.createEvent(new Event(name, description, logo, eventType));
    }

    @GetMapping("/sort/{sortBy}&{ascending}")
    public List<Event> sortEvents(@PathVariable("sortBy") String sortBy, @PathVariable("ascending") boolean ascending) {
        return eventDaoImpl.getSortedEvents(sortBy, ascending);
    }

}


