package com.codecool.CodeCoolProjectGrande.chat.controller;

import com.codecool.CodeCoolProjectGrande.chat.Message;
import com.codecool.CodeCoolProjectGrande.chat.service.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@CrossOrigin
@RequestMapping("/api/chat")
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ChatServiceImpl chatService;

    @Autowired
    public ChatController(SimpMessagingTemplate simpMessagingTemplate, ChatServiceImpl chatService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.chatService = chatService;
    }


    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        chatService.saveMessage(message);
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        chatService.saveMessage(message);
        return message;
    }

    @GetMapping("/public/{senderName}")
    public List<Message> getPublicMessages(@PathVariable String senderName) {
        return chatService.getPublicMessages(senderName);
    }

    @GetMapping("/private/{nickname}")
    public List<Message> getPrivateMessages(@PathVariable String nickname){
        return chatService.getUsersPrivateMessages(nickname);
    }

    @GetMapping("/event/{eventId}")
    public List<Message> getEventMessages(@PathVariable String eventId){
        return chatService.getMessagesSendToEventChat(eventId);
    }
}