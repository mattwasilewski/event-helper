package com.codecool.CodeCoolProjectGrande.data;


import com.codecool.CodeCoolProjectGrande.data.model.MainData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;


@RestController
public class DataController {

    private DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/data")
    public MainData getAllData(){
        String uri = "http://go.wroclaw.pl/api/v1.0/events/1206104/?key=1110736702093623737800883183990674874359";
        dataService.dataRepository.save(new RestTemplateBuilder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build().getForObject(uri, MainData.class));
        return new RestTemplateBuilder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build().getForObject(uri, MainData.class);



    }



}
