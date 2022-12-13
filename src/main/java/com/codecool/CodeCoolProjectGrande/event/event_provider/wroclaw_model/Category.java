package com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class Category implements Serializable
{
    //TODO dodanie place do category
    @ManyToOne
    @JoinColumn(name="place_id")
    public Place place;
    //TODO dodanie offer do category
    @ManyToOne
    @JoinColumn(name="offer_id")
    public Offer offer;
    @Id
    @JsonProperty("id")
    public Integer categoryId;
    @JsonProperty("modified")
    public String modified;
    @JsonProperty("url")
    public String url;
    @JsonProperty("name")
    public String name;
    @JsonProperty("alias")
    public String alias;


}
