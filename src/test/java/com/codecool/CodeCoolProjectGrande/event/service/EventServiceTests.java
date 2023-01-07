package com.codecool.CodeCoolProjectGrande.event.service;


import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.controller.EventController;
import com.codecool.CodeCoolProjectGrande.event.repository.EventRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
class EventServiceTests {


    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private EventController eventController;

    @MockBean
    private EventRepository eventRepository;


    private final Event event =
            Event.builder()
                    .eventId(UUID.randomUUID())
                    .name("Junit test")
                    .description("Junit test")
                    .startDate(LocalDateTime.of(2022, 12, 12, 20, 0))
                    .endDate(LocalDateTime.of(2022, 12, 14, 20, 0))
                    .eventType(EventType.CONCERT)
                    .linkToEventPage("Junit test.com")
                    .price(10)
                    .publicEvent(true)
                    .build();

    @Test
    @Order(1)
    void createEventSuccessTest() {
        when(eventRepository.save(event)).thenReturn(event);
        Assertions.assertEquals(eventService.createEvent(event), new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Test
    @Order(2)
    void createEventFailure() {
        when(eventRepository.findEventByEventId(event.getEventId())).thenReturn(Optional.ofNullable(event));
        Assertions.assertEquals(eventService.createEvent(event), new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Test
    @Order(3)
    void removeEventSuccessTest() {
//        when(eventRepository.removeEventByEventId(event.getEventId())).thenReturn(Optional.of(event));
        when(eventRepository.findEventByEventId(event.getEventId())).thenReturn(Optional.of(event));
        Assertions.assertEquals(eventService.removeEvent(event), new ResponseEntity<>(HttpStatus.OK));
    }

    @Test
    @Order(4)
    void removeEventFailure() {
        when(eventRepository.removeEventByEventId(event.getEventId())).thenReturn(Optional.ofNullable(event));
        Assertions.assertEquals(eventService.removeEvent(event), new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Test
    @Order(5)
    void getAllEventsTest() {
        when(eventRepository.findAll()).thenReturn(Stream.of(event, event).collect(Collectors.toList()));
        Assertions.assertEquals(eventService.getEvents().size(), 2);
    }

    @Test
    @Order(6)
    void getEventByIdTest() {
        when(eventRepository.findEventByEventId(event.getEventId())).thenReturn(Optional.of(event));
        Assertions.assertEquals(eventService.getEventByID(event.getEventId()), Optional.of(event));
    }

    @Test
    @Order(7)
    void getAllEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());
        when(eventRepository.saveAll(events)).thenReturn(events);
        Assertions.assertEquals(eventService.saveAll(events), new ResponseEntity<>(HttpStatus.CREATED));

    }

    @Test
    @Order(8)
    void findEventsByEventTypeTest() {
        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(event);
        when(eventRepository.findEventsByEventType(event.getEventType(), PageRequest.of(1,2))).thenReturn(events);
        Assertions.assertEquals(eventService.findEventsByEventType(event.getEventType(),1,2), events);
    }

    @Test
    @Order(9)
    void sortEventsAscTest() {
        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(event);
        when(eventRepository.findAllByNameContainingOrDescriptionContaining(event.getDescription(),
                event.getName(), PageRequest.of(1,2), Sort.by("name").ascending())).
                thenReturn(events);
        Assertions.assertEquals(eventService.sortEvents("name", true, event.getName(),1 ,2), events);
    }

    @Test
    @Order(10)
    void sortEventsDescTest() {
        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(event);
        when(eventRepository.findAllByNameContainingOrDescriptionContaining(event.getDescription(),
                event.getName(), PageRequest.of(1,2), Sort.by("name").descending())).
                thenReturn(events);
        Assertions.assertEquals(eventService.sortEvents("name", false, event.getName(),1 ,2), events);
    }




}
