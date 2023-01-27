package com.codecool.CodeCoolProjectGrande.event;

import lombok.AllArgsConstructor;
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
public class Image {
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID imageId = UUID.randomUUID();
    @Column(name = "name")
    private String name;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] imageData;



    // getters and setters
}