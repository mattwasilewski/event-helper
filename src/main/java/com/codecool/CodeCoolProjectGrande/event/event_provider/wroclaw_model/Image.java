package com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image {
    @Id
    public UUID imageId = UUID.randomUUID();
    @JsonProperty("standard")
    public String standard = "defaultUrl";
    @JsonProperty("description")
    public String description;

}