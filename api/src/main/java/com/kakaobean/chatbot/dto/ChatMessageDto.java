package com.kakaobean.chatbot.dto;

import lombok.Data;

//얜 api dto - client sent this

@Data
public class ChatMessageDto {
    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
