package com.codecool.CodeCoolProjectGrande.event.event_provider.model;

import java.io.Serializable;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "modified",
            "url",
            "eventDuration",
            "startDate",
            "endDate",
            "location",
            "address",
            "offer",
            "placeName",
            "place",
            "premiere",
            "ticketing",
            "urbancardPremium"
    })
    @Entity
    @Generated("jsonschema2pojo")
    public class ExtendedEvent implements Serializable
    {

        @Id
        public UUID uuid = UUID.randomUUID();
        @JsonProperty("modified")
        public String modified;
        @JsonProperty("url")
        public String url;
        @JsonProperty("eventDuration")
        public String eventDuration;
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
        private final static long serialVersionUID = 1785813041886123000L;

//        public ExtendedEvent(UUID uuid, String startDate, String endDate) {
//            this.name = offer.title;
//            this.description = offer.longDescription;
//            this.logo = offer.images.get(1).standard;
//            this.startDate = startDate;
//            this.endDate = endDate;
//        }
    }
