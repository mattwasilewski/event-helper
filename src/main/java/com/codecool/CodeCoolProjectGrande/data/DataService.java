package com.codecool.CodeCoolProjectGrande.data;


import com.codecool.CodeCoolProjectGrande.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }
}
