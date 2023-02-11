package com.codecool.CodeCoolProjectGrande.chat;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID messageId = UUID.randomUUID();
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    @Enumerated
    private Status status;
}