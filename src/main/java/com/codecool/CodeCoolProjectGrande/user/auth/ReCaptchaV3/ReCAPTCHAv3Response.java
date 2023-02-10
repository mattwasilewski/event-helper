package com.codecool.CodeCoolProjectGrande.user.auth.ReCaptchaV3;

import org.codehaus.jackson.annotate.JsonProperty;

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

    public String getErrors() {
        return errors;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}