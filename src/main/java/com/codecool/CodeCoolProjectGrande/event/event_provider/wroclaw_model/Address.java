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

@JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "city",
            "street",
            "defined"
    })
    @Data
    @NoArgsConstructor
    @Entity
    @Generated("jsonschema2pojo")
    public class Address implements Serializable
    {
        @Id
        public UUID addresId = UUID.randomUUID();
        @JsonProperty("city")
        public String city;
        @JsonProperty("street")
        public String street;
        @JsonProperty("defined")
        public Boolean defined;
        private final static long serialVersionUID = 8846673055482684176L;

    }