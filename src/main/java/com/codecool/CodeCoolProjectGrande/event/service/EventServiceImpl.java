package com.codecool.CodeCoolProjectGrande.event.service;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.event_provider.EventStorage;
import com.codecool.CodeCoolProjectGrande.event.event_provider.global_model.GlobalEvent;
import com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model.WroclawEvent;
import com.codecool.CodeCoolProjectGrande.event.repository.EventRepository;
import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    @Value("${apiWro}")
    private String apiKey;

    @Value("${globalApiKey}")
    private String globalApiKey;

    @Value("${apiWroFirstPage}")
    private int apiWroFirstPage;

    @Value("${apiWroLastPage}")
    private int apiWroLastPage;

    @Value("#{'${globalArtists}'.split(',')}")
    private String[] globalArtists;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    public Optional<Event> getEventByID(UUID eventID) {
        return eventRepository.findEventByEventId(eventID);
    }


    public ResponseEntity<?> createEvent(Event event) {
        if (eventRepository.findEventByName(event.getName()).isEmpty() && !event.getName().contains("3D")
                && !event.getName().contains("dubbing")) {
            eventRepository.save(event);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public Optional<Event> removeEvent(Event event) {
        if (eventRepository.findEventByEventId(event.getEventId()).isPresent()) {
            eventRepository.removeEventByEventId(event.getEventId());
            return Optional.of(event);
        }
        return Optional.empty();
    }

    public List<Event> saveAll(List<Event> events){
        eventRepository.saveAll(events);
        return events;
    }

    public List<Event> findEventsByEventType(EventType eventType, int page, int size){
        return eventRepository.findEventsByEventType(eventType, PageRequest.of(page, size));
    }

    public List<Event> sortEvents(String sortBy, boolean ascending, String phrase, int page, int size) {
        if (ascending) {
            return eventRepository.findAllByNameContainingOrDescriptionContaining(phrase, phrase,
                    PageRequest.of(page, size), Sort.by(sortBy).ascending());
        }
        return eventRepository.findAllByNameContainingOrDescriptionContaining(phrase, phrase,
                PageRequest.of(page, size), Sort.by(sortBy).descending());
    }

    public ResponseEntity<?> assignUserToEvent(Map data) {
        Optional<Event> event = eventRepository.findEventByEventId(UUID.fromString(String.valueOf(data.get("eventId"))));
        Optional<User> user = userRepository.findUserByEmail(String.valueOf(data.get("userEmail")));
        if (event.isPresent() && user.isPresent()) {
            event.get().assignUser(user.get());
            eventRepository.save(event.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> editEventDescriptionByEventId(Map data) {
        Optional<Event> event = eventRepository.findEventByEventId(UUID.fromString(String.valueOf(data.get("eventId"))));
        if(event.isPresent()){
            event.get().setDescription(String.valueOf(data.get("description")));
            eventRepository.save(event.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<String> saveWroclawData() {
        List<String> successfullyAddedEvents = new ArrayList<>();
        for (int startPage = apiWroFirstPage; startPage < apiWroLastPage; startPage++) {
            String uri = String.format("http://go.wroclaw.pl/api/v1.0/events?key=%s&page=%d", apiKey, startPage);
            EventStorage storage = new RestTemplateBuilder()
                    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .build().getForObject(uri, EventStorage.class);
            assert storage != null;
            saveWroclawEvent(storage);
            storage.getItems().forEach(event -> successfullyAddedEvents.add(event.offer.title));
        }
        return successfullyAddedEvents;
    }


    private void saveWroclawEvent(EventStorage storage) {
        storage.getItems().forEach(event -> createEvent(serializeWroclawData(event)));
    }


    public List<String> saveGlobalData() {
        RestTemplate restTemplate = new RestTemplate();
        List<String> successfullyAddedEvents = new ArrayList<>();
        for (String artist : globalArtists) {
            String uri = String.format("https://rest.bandsintown.com/artists/%s/events/?app_id=%s", artist, globalApiKey);
            ResponseEntity<List<GlobalEvent>> rateResponse =
                    restTemplate.exchange(uri,
                            HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                            });
            List<GlobalEvent> events = rateResponse.getBody();
            assert events != null;
            setArtistNameToGlobalEvents(events);
            events.forEach(event -> successfullyAddedEvents.add(event.title));
            saveSerializedGlobalEvents(events);
        }
        return successfullyAddedEvents;
    }

    private void saveSerializedGlobalEvents(List<GlobalEvent> events) {
        List<Event> serializedEvents = events.stream().map(this::serializeGlobalData).toList();
        saveAll(eventsWithoutDuplicateName(serializedEvents));
    }


    private void setArtistNameToGlobalEvents(List<GlobalEvent> events) {
        events.forEach(event -> event.setArtist(events.get(0).getArtist()));
    }

    public List<Event> eventsWithoutDuplicateName(List<Event> events){
        return events.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Event:: getName))),
                        ArrayList::new));
    }

    @NotNull
    public Event serializeWroclawData(WroclawEvent event) {
        return new Event(
                event.offer.title,
                event.offer.longDescription,
                event.offer.url,
                String.format("%s, %s", event.address.street, event.address.city),
                event.offer.mainImage.standard,
                EventType.OTHER,
                event.startDate,
                event.endDate,
                event.location.lattiude,
                event.location.longitude,
                "Generated by wroclaw.pl",
                new HashSet<>());
    }

    @NotNull
    public Event serializeGlobalData(GlobalEvent event) {
        return new Event(
                event.artist.name,
                String.format("%s%s%s", event.description,
                        String.format("<br><a href=\"%s\">Click for event page</a><br><br>", event.url), "<br><br> Generated by bandsintown.com"),
                event.url,
                String.format("%s, %s, %s", event.venue.streetAddress, event.venue.city, event.venue.country),
                event.artist.imageUrl,
                EventType.CONCERT,
                event.datetime,
                event.datetime,
                Double.parseDouble(event.venue.latitude),
                Double.parseDouble(event.venue.longitude),
                "Generated by bandsintown.com",
                new HashSet<>());
    }

    public List<Event> getAssignedEvents(String email) {
        User user = userRepository.findUserByEmail(email).get();
        Set<User> set = new HashSet<>();
        set.add(user);
        return eventRepository.findAllByAssignedUsersIn(set);
    }

    public boolean isUserAssignToEvent(UUID eventId, String userEmail) {   // TODO dokończyć
        User user = userRepository.findUserByEmail(userEmail).get();
        Set<User> set = new HashSet<>();
        set.add(user);
        System.out.println("Service dziala");
        return false; //eventRepository.getEventByEventIdAndAssignedUsersIn(eventId, set).isPresent();
    }


}
