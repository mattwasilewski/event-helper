package com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model;
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

    @Data
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Address implements Serializable
    {
        @Id
        public UUID addressId = UUID.randomUUID();
        @JsonProperty("city")
        public String city;
        @JsonProperty("street")
        public String street;
        @JsonProperty("defined")
        public Boolean defined;

    }