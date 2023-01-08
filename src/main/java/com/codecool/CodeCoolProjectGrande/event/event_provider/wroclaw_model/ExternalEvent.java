package com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    public class ExternalEvent implements Serializable
    {
        @Id
        public UUID uuid = UUID.randomUUID();
        @JsonProperty("url")
        public String url;
        @JsonProperty("startDate")
        public String startDate;
        @JsonProperty("endDate")
        public String endDate;
        @JsonProperty("location")
        @OneToOne(cascade= CascadeType.ALL)
        @JoinColumn(name = "locationId")
        public Location location;
        @JsonProperty("address")
        @OneToOne(cascade= CascadeType.ALL)
        @JoinColumn(name = "addressId")
        public Address address;
        @JsonProperty("offer")
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "offerId")
        public Offer offer;

    }
