package com.codecool.CodeCoolProjectGrande.event.event_provider;


import com.codecool.CodeCoolProjectGrande.event.Event;
import com.codecool.CodeCoolProjectGrande.event.EventType;
import com.codecool.CodeCoolProjectGrande.event.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class DataController {

    private DataService dataService;
    private EventServiceImpl eventService;

    private RestTemplate restTemplate;
    private String uri = "";

    @Autowired
    public DataController(DataService dataService, EventServiceImpl eventService, RestTemplate restTemplate) {
        this.dataService = dataService;
        this.eventService = eventService;
        this.restTemplate = restTemplate;
    }



    @GetMapping(value="/data")
    public EventStorage getEvents() {
        EventStorage storage = new RestTemplateBuilder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build().getForObject(uri, EventStorage.class);
        storage.getItems().forEach(event -> eventService.createEvent(new Event(
                event.offer.title,
                "Test test test test test test",
                event.offer.images.toString(),
                EventType.CONCERT,
                event.startDate,
                event.endDate)));
        dataService.dataRepository.saveAll(storage.getItems());
        return storage;

    }




}



//    @GetMapping(value="/data")
//    public EventStorage getEvents() {
//        EventStorage storage = new RestTemplateBuilder()
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .build().getForObject(uri, EventStorage.class);
//        dataService.dataRepository.saveAll(storage.getItems());
//        return new RestTemplateBuilder()
//                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
//                .build().getForObject(uri, EventStorage.class);
//
//    }
