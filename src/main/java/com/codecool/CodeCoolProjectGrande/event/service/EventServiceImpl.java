package com.codecool.CodeCoolProjectGrande.event.service;

import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.event_provider.EventStorage;
import com.codecool.CodeCoolProjectGrande.event.event_provider.global_model.GlobalEvent;
import com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model.ExternalEvent;
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
    @Value("${apiKey}")
    private String apiKey;

    @Value("${globalApiKey}")
    private String globalApiKey;

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


    public void createEvent(Event event) {
        if (!eventRepository.findEventByName(event.getName()).isPresent()) {
            eventRepository.save(event);
        }
    }

    public void saveAll(List<Event> events){
        eventRepository.saveAll(events);
    }

    public List<Event> findEventsByEventType(EventType eventType){
        return eventRepository.findEventsByEventType(eventType);
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
        Optional<User> user = userRepository.findUserByUserId(UUID.fromString(String.valueOf(data.get("userId"))));
        //TODO czy zwracac responseentity/ true/false
        if (event.isPresent() && user.isPresent()) {
            event.get().assignUser(user.get());
            eventRepository.save(event.get());
            return new ResponseEntity<>(HttpStatus.OK); // TODO czy zwracać statusy http albo true/false albo void
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> editEventDescriptionByEventId(Map data) {
        Optional<Event> event = eventRepository.findEventByEventId(UUID.fromString(String.valueOf(data.get("eventId"))));
        if(event.isPresent()){
            event.get().setDescription(String.valueOf(data.get("description")));
            eventRepository.save(event.get());
            return new ResponseEntity<>(HttpStatus.OK);     // TODO to samo co wyżej
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    public void saveWroclawData() {
        int firstPage = 10;
        int lastPage = 20;
        for (int startPage = firstPage; startPage < lastPage; startPage++) {
            String uri = String.format("http://go.wroclaw.pl/api/v1.0/events?key=%s&page=%d", apiKey, startPage);
            EventStorage storage = new RestTemplateBuilder()
                    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .build().getForObject(uri, EventStorage.class);
            assert storage != null;
            storage.getItems().forEach(event -> createEvent(serializeWroclawData(event)));
        }
    }


    public void saveGlobalData() {
        RestTemplate restTemplate = new RestTemplate();
//        String[] artists = {"marcocarola", "edsheeran", "arcticmonkeys","bradwilliams", "war", "bobmalone",
//                "justinbieber", "thrice", "redhotchilipeppers", "afi", "keshi"};
        String[] artists = {"bobmalone"};
        for (String artist : artists) {
            String uri = String.format("https://rest.bandsintown.com/artists/%s/events/?app_id=%s", artist, globalApiKey);
//            GlobalEvent globalEvent = new RestTemplateBuilder()
//                    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                    .build().getForObject(uri, GlobalEvent.class);
            ResponseEntity<List<GlobalEvent>> rateResponse =
                    restTemplate.exchange(uri,
                            HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                            });
            List<GlobalEvent> events = rateResponse.getBody();
            assert events != null;
            events.forEach(event -> event.setArtist(events.get(0).getArtist()));
            events.forEach(event -> System.out.println(serializeGlobalData(event)));
            List<Event> serializedEvents = events.stream().map(this::serializeGlobalData).toList();
            saveAll(serializedEvents);
        }
    }

    @NotNull
    private Event serializeWroclawData(ExternalEvent event) {
        return new Event(
                event.offer.title,
                event.offer.longDescription,
                event.offer.url,
                String.format("%s, %s", event.address.street, event.address.city),
                event.offer.mainImage.standard,
                EventType.CONCERT,
                event.startDate,
                event.endDate,
                event.location.lattiude,
                event.location.longitude);
    }

    @NotNull
    private Event serializeGlobalData(GlobalEvent event) {
        return new Event(
                event.title,
                event.description,
                event.url,
                String.format("%s, %s, %s", event.venue.streetAddress, event.venue.city, event.venue.country),
                event.artist.imageUrl,
                EventType.CONCERT,
                event.festivalStartDate,
                event.festivalEndDate,
                Double.parseDouble(event.venue.latitude),
                Double.parseDouble(event.venue.longitude));
    }



}
