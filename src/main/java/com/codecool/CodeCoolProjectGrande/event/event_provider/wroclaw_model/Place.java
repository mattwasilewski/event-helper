package com.codecool.CodeCoolProjectGrande.event.event_provider.wroclaw_model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class Place implements Serializable {
    @Id
    @JsonProperty("id")
    public Integer placeId;
    @JsonProperty("modified")
    public String modified;
    @JsonProperty("url")
    public String url;
    @JsonProperty("title")
    public String title;
    @JsonProperty("alias")
    public String alias;
    @JsonProperty("longDescription")
    public String longDescription;
    @JsonProperty("pageLink")
    public String pageLink;
    @JsonProperty("categories")
    @OneToMany(mappedBy = "place", fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    public List<Category> categories = new ArrayList<>();
    @JsonProperty("priority")
    public Integer priority;
    @JsonProperty("source")
    public String source;
    @JsonProperty("language")
    public String language;
    @JsonProperty("lastPublished")
    public String lastPublished;

}