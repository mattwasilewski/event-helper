package com.codecool.CodeCoolProjectGrande.event.service;


import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class EventServiceTests {


    @Autowired()
    private EventServiceImpl eventService;

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
                    .publicEvent(true).build();

    @Test
    @Order(1)
    void createEventTest() {
        eventService.createEvent(event);
        Assertions.assertTrue(eventService.getEventByID(event.getEventId()).isPresent());
    }

    @Test
    @Order(2)
    void removeEventTest() {               // TODO not working
        eventService.removeEvent(event);
        Assertions.assertFalse(eventService.getEventByID(event.getEventId()).isPresent());
    }



}
