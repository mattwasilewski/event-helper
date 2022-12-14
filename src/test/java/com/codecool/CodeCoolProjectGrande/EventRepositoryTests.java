package com.codecool.CodeCoolProjectGrande;


import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.repository.EventRepository;
import com.codecool.CodeCoolProjectGrande.event.service.EventServiceImpl;
import de.codecentric.boot.admin.client.registration.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class EventRepositoryTests {


    @Autowired()
    private EventServiceImpl eventService;

    private final Event event =
            Event.builder()
                    .eventId(UUID.randomUUID())
                    .name("Test")
                    .description("Test")
                    .startDate(LocalDateTime.of(2022, 12, 12, 20, 0))
                    .endDate(LocalDateTime.of(2022, 12, 14, 20, 0))
                    .eventType(EventType.CONCERT)
                    .linkToEventPage("link.pl")
                    .price(10)
                    .publicEvent(true).build();

    @Test
    public void createEventTest() {
        eventService.createEvent(event);
        Assertions.assertTrue(eventService.getEventByID(event.getEventId()).isPresent());
    }

}
