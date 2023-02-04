package com.codecool.CodeCoolProjectGrande.event.service;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventStatus;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.image.Image;
import com.codecool.CodeCoolProjectGrande.event.event_provider.EventStorage;
import com.codecool.CodeCoolProjectGrande.event.event_provider.global_model.GlobalEvent;
import com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model.WroclawEvent;
import com.codecool.CodeCoolProjectGrande.event.repository.EventRepository;
import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
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

    public Optional<Event> createEvent(Event event) {
        return createEvent(event, null);
    }

    public Optional<Event> createEvent(Event event, String userEmail) {
        if (userEmail != null) {
            Optional<User> user = userRepository.findUserByEmail(userEmail);
            user.ifPresent(value -> event.setUserId(value.getUserId()));
        }
        if (checkEventName(event)) {
            eventRepository.save(event);
            return Optional.of(event);
        }
        return Optional.empty();
    }

    private boolean checkEventName(Event event) {
        return eventRepository.findEventByName(event.getName()).isEmpty() && !event.getName().contains("3D")
                && !event.getName().contains("dubbing");
    }

    public Optional<Event> removeEvent(Event event) {
        if (eventRepository.findEventByEventId(event.getEventId()).isPresent()) {
            eventRepository.removeEventByEventId(event.getEventId());
            return Optional.of(event);
        }
        return Optional.empty();
    }

    public ResponseEntity<?> deleteEvent(String userEmail, UUID eventId) {
        Optional<User> user = userRepository.findUserByEmail(userEmail);
        if (user.isPresent() && checkOwner(user.get().getUserId(), eventId)) {
            eventRepository.removeEventByEventId(eventId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private boolean checkOwner(UUID userId, UUID eventId) {
        return eventRepository.getEventByEventIdAndUserId(eventId, userId).isPresent();
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

    public Optional<Event> assignUserToEvent(Map data) {
        Optional<Event> event = eventRepository.findEventByEventId(UUID.fromString(String.valueOf(data.get("eventId"))));
        Optional<User> user = userRepository.findUserByEmail(String.valueOf(data.get("userEmail")));
        if (event.isPresent() && user.isPresent()) {
            if (isUserAssignToEvent(UUID.fromString(String.valueOf(data.get("eventId"))), String.valueOf(data.get("userEmail")))) {
                event.get().removeUSer(user.get());
                eventRepository.save(event.get());
            } else {
                event.get().assignUser(user.get());
                eventRepository.save(event.get());
            }
            return event;
        }
        return Optional.empty();
    }
    public List<String> getPolandCities(){
        List<String> cityNames = new ArrayList<>();
        try{
            URL url = new URL("http://api.geonames.org/searchJSON?country=PL&featureCode=PPLA&username=devgraba");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(conn.getInputStream());
                JsonNode citiesNode = rootNode.path("geonames");
                for (JsonNode cityNode : citiesNode) {
                    cityNames.add(cityNode.path("name").asText());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return cityNames;
    }

    public Optional<Event> editEventDescriptionByEventId(Map data) {
        Optional<Event> event = eventRepository.findEventByEventId(UUID.fromString(String.valueOf(data.get("eventId"))));
        if(event.isPresent()){
            event.get().setDescription(String.valueOf(data.get("description")));
            eventRepository.save(event.get());
            return event;
        }
        return Optional.empty();
    }

    public Optional<Event> setEventStatus(@RequestBody Map data) {
        Optional<Event> event = eventRepository.findEventByEventId(UUID.fromString(String.valueOf(data.get("eventId"))));
        if(event.isPresent()){
            event.get().setEventStatus(EventStatus.valueOf(String.valueOf(data.get("eventStatus"))));
            eventRepository.save(event.get());
            return event;
        } else {
            return Optional.empty();
        }
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


    public EventStorage saveWroclawEvent(EventStorage storage) {
        storage.getItems().forEach(event -> createEvent(serializeWroclawData(event)));
        return storage;
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

    public List<Event> saveSerializedGlobalEvents(List<GlobalEvent> events) {
        List<Event> serializedEvents = events.stream().map(this::serializeGlobalData).toList();
        saveAll(eventsWithoutDuplicateName(serializedEvents));
        return serializedEvents;
    }


    public List<GlobalEvent> setArtistNameToGlobalEvents(List<GlobalEvent> events) {
        events.forEach(event -> event.setArtist(events.get(0).getArtist()));
        return events;
    }

    public List<Event> eventsWithoutDuplicateName(List<Event> events) {
        return events.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Event::getName))),
                        ArrayList::new));
    }

    public Event serializeWroclawData(WroclawEvent event) {
        Image image = Image.builder()
                .imageId(UUID.randomUUID())
                .imageUrl(event.offer.mainImage.imageUrl)
                .build();
        return new Event(
                event.offer.title,
                event.offer.longDescription,
                event.offer.url,
                String.format("%s, %s", event.address.street, event.address.city),
                image,
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
        Image image = Image.builder()
                .imageId(UUID.randomUUID())
                .imageUrl(event.artist.imageUrl)
                .build();
        return new Event(
                event.artist.name,
                String.format("%s%s%s", event.description,
                        String.format("<br><a href=\"%s\">Click for event page</a><br><br>", event.url), "<br><br> Generated by bandsintown.com"),
                event.url,
                String.format("%s, %s, %s", event.venue.streetAddress, event.venue.city, event.venue.country),
                image,
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

    public boolean isUserAssignToEvent(UUID eventId, String userEmail) {
        User user = userRepository.findUserByEmail(userEmail).get();
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        return eventRepository.getEventByEventIdAndAssignedUsersIn(eventId, userSet).isPresent();
    }

    public int getNumOfAttendees(UUID eventId) {
        return eventRepository.getNumOfAttendees(String.valueOf(eventId));
    }



}
