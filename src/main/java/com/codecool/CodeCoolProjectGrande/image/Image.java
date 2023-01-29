package com.codecool.CodeCoolProjectGrande.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Image {
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID imageId = UUID.randomUUID();
    @Column(name = "name")
    private String name;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] imageData;
    @JsonProperty("standard")
    public String imageUrl = "defaultUrl";
    @JsonProperty("description")
    public String description;

}