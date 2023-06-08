package com.kakaobean.core.chatbot.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class QuestionRequestDto implements Serializable {

    private String question;

    public QuestionRequestDto(String question) {
        this.question = question;
    }
}