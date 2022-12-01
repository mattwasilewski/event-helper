package com.codecool.CodeCoolProjectGrande.event_provider;


import org.springframework.stereotype.Service;

@Service
public class DataService {
    DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }
}
