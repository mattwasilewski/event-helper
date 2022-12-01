package com.codecool.CodeCoolProjectGrande.data.model;


import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "disabledAccessAvailable",
            "carParkAvailable",
            "foodAndDrinkAvailable"
    })
    @Generated("jsonschema2pojo")
    public class Venue implements Serializable
    {

        @JsonProperty("disabledAccessAvailable")
        public Boolean disabledAccessAvailable;
        @JsonProperty("carParkAvailable")
        public Boolean carParkAvailable;
        @JsonProperty("foodAndDrinkAvailable")
        public Boolean foodAndDrinkAvailable;
        private final static long serialVersionUID = 4041938954876607192L;

    }