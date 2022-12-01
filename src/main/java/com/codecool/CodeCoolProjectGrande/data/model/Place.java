package com.codecool.CodeCoolProjectGrande.data.model;

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
        "longDescription",
        "pageLink",
        "type",
        "categories",
        "images",
        "venue",
        "priority",
        "source",
        "language",
        "location",
        "address",
        "lastPublished"
})
@Generated("jsonschema2pojo")
public class Place implements Serializable {

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
    @JsonProperty("longDescription")
    public String longDescription;
    @JsonProperty("pageLink")
    public String pageLink;
    @JsonProperty("type")
    public Type__1 type;
    @JsonProperty("categories")
    public List<Object> categories = null;
    @JsonProperty("images")
    public List<Object> images = null;
    @JsonProperty("venue")
    public Venue venue;
    @JsonProperty("priority")
    public Integer priority;
    @JsonProperty("source")
    public String source;
    @JsonProperty("language")
    public String language;
    @JsonProperty("location")
    public Location__1 location;
    @JsonProperty("address")
    public Address__1 address;
    @JsonProperty("lastPublished")
    public String lastPublished;
    private final static long serialVersionUID = 4255659379628115253L;

}