package com.codecool.CodeCoolProjectGrande.event_provider.model;

import java.io.Serializable;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({

})
@Entity
@Data
@NoArgsConstructor
@Generated("jsonschema2pojo")
public class Show implements Serializable {
    @Id
    public UUID showId = UUID.randomUUID();
    private final static long serialVersionUID = -2797143017967821021L;

}