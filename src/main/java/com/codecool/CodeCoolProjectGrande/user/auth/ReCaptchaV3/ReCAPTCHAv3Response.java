package com.codecool.CodeCoolProjectGrande.user.auth.ReCaptchaV3;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;


@Data
public class ReCAPTCHAv3Response {

    @JsonProperty( "success" )
    private boolean success;

    @JsonProperty( "score" )
    private double score;

    @JsonProperty( "action" )
    private String action;

    @JsonProperty( "challenge_ts" )
    private Object timestamp;

    @JsonProperty( "hostname" )
    private String hostname;

    @JsonProperty( "error-codes" )
    private String errors;

}