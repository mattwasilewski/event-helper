package com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model;

import java.io.Serializable;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category implements Serializable
{
    @Id
    @JsonProperty("id")
    public Integer categoryId;
    @JsonProperty("url")
    public String url;
    @JsonProperty("name")
    public String name;


}
