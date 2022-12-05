package com.codecool.CodeCoolProjectGrande.event.event_provider.model;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;

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
            "alias",
            "language"
    })
    @Entity
    @Data
    @NoArgsConstructor
    @Generated("jsonschema2pojo")

    public class Type implements Serializable
    {

        @JsonProperty("id")
        @Id
        public Integer typeId;
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
