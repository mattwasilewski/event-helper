package com.codecool.CodeCoolProjectGrande.event.event_provider.model;


import java.io.Serializable;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "disabledAccessAvailable",
            "carParkAvailable",
            "foodAndDrinkAvailable"
    })
    @Entity
    @Data
    @NoArgsConstructor
    @Generated("jsonschema2pojo")
    public class Venue implements Serializable
    {
        @Id
        public UUID venueId = UUID.randomUUID();
        @JsonProperty("disabledAccessAvailable")
        public Boolean disabledAccessAvailable;
        @JsonProperty("carParkAvailable")
        public Boolean carParkAvailable;
        @JsonProperty("foodAndDrinkAvailable")
        public Boolean foodAndDrinkAvailable;
        private final static long serialVersionUID = 4041938954876607192L;

    }