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
@JsonPropertyOrder({
        "id",
        "modified",
        "url",
        "title",
        "alias",
        "externalLink",
        "pageLink",
        "type",
        "categories",
        "images",
        "show",
        "priority",
        "source",
        "language",
        "offerType",
        "lastPublished"
})
@Data
@NoArgsConstructor
@Entity
@Generated("jsonschema2pojo")
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
    @JsonProperty("externalLink")
    public String externalLink;
    @JsonProperty("pageLink")
    public String pageLink;

    @JsonProperty("categories")
    @OneToMany(mappedBy = "offer", fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    public List<Category> categories = new ArrayList<>();


    @JsonProperty("images")
    @OneToMany(mappedBy = "offer", fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    public List<Image> images = new ArrayList<>();

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
    private final static long serialVersionUID = 2499121473705691828L;


}
