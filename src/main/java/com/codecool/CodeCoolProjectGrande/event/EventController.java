package com.codecool.CodeCoolProjectGrande.event;

import com.codecool.CodeCoolProjectGrande.account.user.User;
import com.codecool.CodeCoolProjectGrande.account.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
@RestController
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
}
