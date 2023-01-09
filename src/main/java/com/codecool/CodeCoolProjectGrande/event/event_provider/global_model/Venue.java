package com.codecool.CodeCoolProjectGrande.event.event_provider.global_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Id;
import java.util.UUID;

public class Venue {

    @Id
    public UUID uuid = UUID.randomUUID();
    @JsonProperty("location")
    public String location;
    @JsonProperty("name")
    public String name;
    @JsonProperty("latitude")
    public String latitude;
    @JsonProperty("longitude")
    public String longitude;
    @JsonProperty("street_address")
    public String streetAddress;
    @JsonProperty("city")
    public String city;
    @JsonProperty("country")
    public String country;

}
