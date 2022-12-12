package com.codecool.CodeCoolProjectGrande.event.event_provider.global_model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Artist {
    @Id
    public UUID uuid = UUID.randomUUID();
    @JsonProperty("name")
    public String name;
    @JsonProperty("image_url")
    public String imageUrl;
}
