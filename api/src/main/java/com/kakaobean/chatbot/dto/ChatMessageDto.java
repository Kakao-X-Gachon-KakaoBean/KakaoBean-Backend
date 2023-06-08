package com.kakaobean.chatbot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageDto {

    private String content;

    public ChatMessageDto(String content) {
        this.content = content;
    }
}
