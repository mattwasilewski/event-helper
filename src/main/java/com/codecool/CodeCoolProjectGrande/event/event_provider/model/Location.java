package com.codecool.CodeCoolProjectGrande.event.event_provider.model;

import java.io.Serializable;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "lattiude",
            "longitude",
            "defined"
    })
    @Data
    @NoArgsConstructor
    @Entity
    @Generated("jsonschema2pojo")
    public class Location implements Serializable
    {
        @Id
        private UUID locationId = UUID.randomUUID();
        @JsonProperty("lattiude")
        public Double lattiude;
        @JsonProperty("longitude")
        public Double longitude;
        @JsonProperty("defined")
        public Boolean defined;
        private final static long serialVersionUID = 7552324809068724044L;

    }