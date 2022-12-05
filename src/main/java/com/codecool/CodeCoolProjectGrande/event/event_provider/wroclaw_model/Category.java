package com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model;

import java.io.Serializable;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "modified",
        "url",
        "name",
        "alias"
})

@Data
@NoArgsConstructor
@Entity
@Generated("jsonschema2pojo")
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
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6884763678085507016L;

//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }

}
