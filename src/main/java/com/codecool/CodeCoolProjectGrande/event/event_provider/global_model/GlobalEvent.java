package com.codecool.CodeCoolProjectGrande.event.event_provider.global_model;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GlobalEvent {

    @Id
    public UUID uuid = UUID.randomUUID();
    @JsonProperty("url")
    public String url;
    @JsonProperty("datetime")
    public String datetime;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;

    @JsonProperty("artist")
    public Artist artist = new Artist();
    @JsonProperty("venue")
    public Venue venue;
    @JsonProperty("artist_id")
    public String artistId;
    @JsonProperty("on_sale_datetime")
    public String onSaleDatetime;
    @JsonProperty("festival_start_date")
    public String festivalStartDate;
    @JsonProperty("festival_end_date")
    public String festivalEndDate;
    @JsonProperty("festival_datetime_display_rule")
    public String festivalDatetimeDisplayRule;
    @JsonProperty("starts_at")
    public String startsAt;
    @JsonProperty("ends_at")
    public String endsAt;
    @JsonProperty("datetime_display_rule")
    public String datetimeDisplayRule;

}


