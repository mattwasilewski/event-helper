package com.codecool.CodeCoolProjectGrande.event;

import com.codecool.CodeCoolProjectGrande.user.User;
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
        return eventDaoImpl.getEvents();
    }

    @GetMapping("{eventID}")
    public Optional<Event> getEventByID(@PathVariable UUID eventID) {
        return eventDaoImpl.getEventByID(eventID);
    }

    @PostMapping("create-event")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        eventDaoImpl.createEvent(event);
        return new ResponseEntity<>("Event added", HttpStatus.OK);
    }


    @GetMapping("/sort/{sortBy}&{ascending}")
    public List<Event> sortEvents(@PathVariable("sortBy") String sortBy, @PathVariable("ascending") boolean ascending) {
        return eventDaoImpl.getSortedEvents(sortBy, ascending);
    }

}


