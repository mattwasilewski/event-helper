package com.codecool.CodeCoolProjectGrande.event_provider.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "standard",
        "large",
        "thumbnail",
        "tile",
        "banner",
        "description",
        "role"
})
@Generated("jsonschema2pojo")
@Data
@NoArgsConstructor
@Entity
public class Image {
    @Id
    public UUID imageId = UUID.randomUUID();
    @JsonProperty("standard")
    public String standard;
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
    @ManyToOne
    @JoinColumn(name = "offer_id")
    public Offer offer;

}