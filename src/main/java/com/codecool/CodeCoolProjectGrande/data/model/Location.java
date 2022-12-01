package com.codecool.CodeCoolProjectGrande.data.model;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "lattiude",
            "longitude",
            "defined"
    })
    @Generated("jsonschema2pojo")
    public class Location implements Serializable
    {

        @JsonProperty("lattiude")
        public Double lattiude;
        @JsonProperty("longitude")
        public Double longitude;
        @JsonProperty("defined")
        public Boolean defined;
        private final static long serialVersionUID = 7552324809068724044L;

    }