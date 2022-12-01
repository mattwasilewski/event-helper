package com.codecool.CodeCoolProjectGrande.event_provider;


import com.codecool.CodeCoolProjectGrande.event_provider.model.MainData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;


@RestController
public class DataController {

    private DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/data")
    public MainData getData(){
        String uri = "";
        dataService.dataRepository.save(new RestTemplateBuilder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build().getForObject(uri, MainData.class));
        return new RestTemplateBuilder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build().getForObject(uri, MainData.class);



    }


}
