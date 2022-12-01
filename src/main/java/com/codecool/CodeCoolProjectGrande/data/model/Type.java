package com.codecool.CodeCoolProjectGrande.data.model;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "modified",
            "url",
            "name",
            "alias",
            "language"
    })
    @Generated("jsonschema2pojo")
    public class Type implements Serializable
    {

        @JsonProperty("id")
        public Integer id;
        @JsonProperty("modified")
        public String modified;
        @JsonProperty("url")
        public String url;
        @JsonProperty("name")
        public String name;
        @JsonProperty("alias")
        public String alias;
        @JsonProperty("language")
        public String language;
        private final static long serialVersionUID = 2291376821786884256L;
}
