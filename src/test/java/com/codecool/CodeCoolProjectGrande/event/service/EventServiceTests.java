package com.codecool.CodeCoolProjectGrande.event.service;


import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.controller.EventController;
import com.codecool.CodeCoolProjectGrande.event.repository.EventRepository;
import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.UserType;
import com.codecool.CodeCoolProjectGrande.user.password_reset.ResetPasswordToken;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
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
import java.util.*;
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


    @MockBean
    UserRepository userRepository;



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
                    .assignedUsers(new HashSet<>())
                    .build();


    private final User user = User.builder().userId(UUID.randomUUID())
            .name("Test")
            .age(22)
            .email("email@gmail.com")
            .password("test1")
            .userType(UserType.USER)
            .location("Warsaw")
            .resetPasswordToken(new ResetPasswordToken())
            .build();


    @Test
    void createEventSuccessTest() {
        when(eventRepository.save(event)).thenReturn(event);
        Assertions.assertEquals(eventService.createEvent(event), new ResponseEntity<>(HttpStatus.CREATED));
    }

    @Test
    void createEventFailure() {
        when(eventRepository.findEventByName(event.getName())).thenReturn(Optional.of(event));
        Assertions.assertEquals(eventService.createEvent(event), new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Test
    void removeEventSuccessTest() {
        when(eventRepository.findEventByEventId(event.getEventId())).thenReturn(Optional.of(event));
        Assertions.assertEquals(eventService.removeEvent(event), Optional.of(event));
    }

    @Test
    void removeEventFailureTest() {
        when(eventRepository.removeEventByEventId(event.getEventId())).thenReturn(Optional.empty());
        Assertions.assertEquals(eventService.removeEvent(event), Optional.empty());
    }


    @Test
    void getAllEventsTest() {
        when(eventRepository.findAll()).thenReturn(Stream.of(event, event).collect(Collectors.toList()));
        Assertions.assertEquals(eventService.getEvents().size(), 2);
    }

    @Test
    void getEventByIdTest() {
        when(eventRepository.findEventByEventId(event.getEventId())).thenReturn(Optional.of(event));
        Assertions.assertEquals(eventService.getEventByID(event.getEventId()), Optional.of(event));
    }

    @Test
    void getAllEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());
        when(eventRepository.saveAll(events)).thenReturn(events);
        Assertions.assertEquals(eventService.saveAll(events), events);

    }

    @Test
    void findEventsByEventTypeTest() {
        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(event);
        when(eventRepository.findEventsByEventType(event.getEventType(), PageRequest.of(1,2))).thenReturn(events);
        Assertions.assertEquals(eventService.findEventsByEventType(event.getEventType(),1,2), events);
    }

    @Test
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

    @Test
    @Order(11)
    void assignUserToEventSuccessfullyTest() {
        event.assignUser(user);
        Map<Object, Object> data = new HashMap<>();
        data.put("eventId", event.getEventId());
        data.put("userEmail", user.getEmail());
        when(eventRepository.findEventByEventId(event.getEventId())).thenReturn(Optional.of(event));
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Assertions.assertEquals(eventService.assignUserToEvent(data), new ResponseEntity<>(HttpStatus.OK));
    }

    @Test
    void assignUserToEventFailureUserNotExistTest() {
        event.assignUser(user);
        String mockFailEmail = "test@test.pl";
        Map<Object, Object> data = new HashMap<>();
        data.put("eventId", event.getEventId());
        data.put("userEmail", user.getEmail());
        when(eventRepository.findEventByEventId(event.getEventId())).thenReturn(Optional.of(event));
        when(userRepository.findUserByEmail(mockFailEmail)).thenReturn(Optional.empty());
        Assertions.assertEquals(eventService.assignUserToEvent(data), new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Test
    void assignUserToEventFailureEventNotExistTest() {
        event.assignUser(user);
        UUID mockEventId = UUID.randomUUID();
        Map<Object, Object> data = new HashMap<>();
        data.put("eventId", event.getEventId());
        data.put("userEmail", user.getEmail());
        when(eventRepository.findEventByEventId(mockEventId)).thenReturn(Optional.empty());
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Assertions.assertEquals(eventService.assignUserToEvent(data), new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Test
    void editEventDescriptionByEventIdSuccessfullyTest() {
        Map<Object, Object> data = new HashMap<>();
        data.put("eventId", event.getEventId());
        data.put("userEmail", user.getEmail());
        when(eventRepository.findEventByEventId(event.getEventId())).thenReturn(Optional.of(event));
        Assertions.assertEquals(eventService.editEventDescriptionByEventId(data), new ResponseEntity<>(HttpStatus.OK));


    }


    @Test
    void editEventDescriptionByEventIdFailureTest() {
        UUID mockNotExistEventId = UUID.randomUUID();
        Map<Object, Object> data = new HashMap<>();
        data.put("eventId", event.getEventId());
        data.put("userEmail", user.getEmail());
        when(eventRepository.findEventByEventId(mockNotExistEventId)).thenReturn(Optional.empty());
        Assertions.assertEquals(eventService.editEventDescriptionByEventId(data), new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }



    //TODO refactor and right tests
    @Test
    void saveGlobalDataSuccessfullyTest(){
        List<String> artists = Stream.of("test", "test").toList();
        when(eventService.saveGlobalData()).thenReturn(artists);
        Assertions.assertEquals(eventController.saveGlobalData(), new ResponseEntity<>(HttpStatus.CREATED));
    }

    /*
    @Test
    void saveWroclawDataSuccessfullyTest(){
        List<String> titles = Stream.of("test", "test").toList();
        when(eventService.saveWroclawData()).thenReturn(titles);
        when(eventRepository.findEventByName(event.getName())).thenReturn(Optional.empty());
        Assertions.assertEquals(eventController.saveWroclawData(), new ResponseEntity<>(HttpStatus.CREATED));
    }
    */


    @Test
    void getAssignedEventsSuccessfully() {
        List<Event> events = new ArrayList<>();
        Set<User> users = new HashSet<>();
        users.add(user);
        events.add(event);
        events.add(new Event());
        event.setAssignedUsers(users);
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(eventRepository.findAllByAssignedUsersIn(users)).thenReturn(events);
        Assertions.assertEquals(eventService.getAssignedEvents(user.getEmail()), events);
    }

}
