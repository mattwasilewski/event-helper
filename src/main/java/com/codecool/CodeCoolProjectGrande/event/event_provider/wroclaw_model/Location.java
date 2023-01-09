package com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Location implements Serializable
    {
        @Id
        public UUID uuid = UUID.randomUUID();
        @JsonProperty("lattiude")
        public Double lattiude;
        @JsonProperty("longitude")
        public Double longitude;
        @JsonProperty("defined")
        public Boolean defined;

    }