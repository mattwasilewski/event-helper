package com.codecool.CodeCoolProjectGrande.event_provider.model;

import java.io.Serializable;
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
    public class MainData implements Serializable
    {

        @Id
        @JsonProperty("id")
        public Long id;
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
        @JsonProperty("placeName")
        public String placeName;
        @JsonProperty("place")
        @OneToOne(cascade= CascadeType.ALL)
        @JoinColumn(name = "placeId")
        public Place place;
        @JsonProperty("premiere")
        public Boolean premiere;
        @JsonProperty("ticketing")
        public String ticketing;
        @JsonProperty("urbancardPremium")
        public Boolean urbancardPremium;
        private final static long serialVersionUID = 1785813041886123000L;

    }
