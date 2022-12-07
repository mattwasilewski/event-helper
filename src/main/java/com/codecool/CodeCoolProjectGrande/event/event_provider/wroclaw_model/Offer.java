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
public class Offer implements Serializable {

    @JsonProperty("id")
    @Id
    public Integer id;
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
    @JsonProperty("externalLink")
    public String externalLink;
    @JsonProperty("pageLink")
    public String pageLink;

    @JsonProperty("categories")
    @OneToMany(mappedBy = "offer", fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    public List<Category> categories = new ArrayList<>();
    @JsonProperty("mainImage")
    @OneToOne(mappedBy = "offer", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    public Image mainImage = new Image();

    @JsonProperty("priority")
    public Integer priority;
    @JsonProperty("source")
    public String source;
    @JsonProperty("language")
    public String language;
    @JsonProperty("offerType")
    public String offerType;
    @JsonProperty("lastPublished")
    public String lastPublished;


}
