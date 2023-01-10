package com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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