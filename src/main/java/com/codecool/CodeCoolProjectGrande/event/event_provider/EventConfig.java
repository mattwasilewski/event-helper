package com.codecool.CodeCoolProjectGrande.event.event_provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
