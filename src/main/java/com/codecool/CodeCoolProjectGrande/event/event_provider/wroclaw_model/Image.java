package com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Image {
    @Id
    public UUID imageId = UUID.randomUUID();
    @JsonProperty("standard")
    public String standard = "defaultUrl";
    @JsonProperty("large")
    public String large;
    @JsonProperty("thumbnail")
    public String thumbnail;
    @JsonProperty("tile")
    public String tile;
    @JsonProperty("banner")
    public String banner;
    @JsonProperty("description")
    public String description;
    @JsonProperty("role")
    public String role;
    @OneToOne
    @JoinColumn(name = "offer_id")
    public Offer offer;

}