package com.codecool.CodeCoolProjectGrande.data.model;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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
        public Location location;
        @JsonProperty("address")
        public Address address;
        @JsonProperty("offer")
        public Offer offer;
        @JsonProperty("placeName")
        public String placeName;
        @JsonProperty("place")
        public Place place;
        @JsonProperty("premiere")
        public Boolean premiere;
        @JsonProperty("ticketing")
        public String ticketing;
        @JsonProperty("urbancardPremium")
        public Boolean urbancardPremium;
        private final static long serialVersionUID = 1785813041886123000L;

    }
