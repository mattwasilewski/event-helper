package com.codecool.CodeCoolProjectGrande.event_provider.model;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@Generated("jsonschema2pojo")
public class Offer implements Serializable {

    @JsonProperty("id")
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
    @JsonProperty("type")
    public Type type;
    @JsonProperty("categories")
    public List<Object> categories = null;
    @JsonProperty("images")
    public List<Object> images = null;
    @JsonProperty("show")
    public Show show;
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
