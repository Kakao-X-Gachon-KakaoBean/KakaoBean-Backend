package com.kakaobean.core.chatGpt.application.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class QuestionRequestDto implements Serializable {
    private String question;
}