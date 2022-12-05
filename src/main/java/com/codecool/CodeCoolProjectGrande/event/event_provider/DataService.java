package com.codecool.CodeCoolProjectGrande.event.event_provider;


import com.codecool.CodeCoolProjectGrande.event.event_provider.model.ExtendedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DataService {
    DataRepository dataRepository;
    RestTemplate restTemplate;

    public DataService(DataRepository dataRepository, RestTemplate restTemplate) {
        this.dataRepository = dataRepository;
        this.restTemplate = restTemplate;
    }


    public List<ExtendedEvent> findAll() {
        return dataRepository.findAll();
    }

    public void saveAll(List<ExtendedEvent> todos) {
        dataRepository.saveAll(todos);
    }

}

