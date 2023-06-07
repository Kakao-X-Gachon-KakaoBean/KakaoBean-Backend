package com.kakaobean.chatbot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//얜 api dto - client sent this

@Getter
@NoArgsConstructor
public class ChatMessageDto {

    private String content;

    public ChatMessageDto(String content) {
        this.content = content;
    }
}
