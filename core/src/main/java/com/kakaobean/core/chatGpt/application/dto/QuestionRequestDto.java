package com.kakaobean.core.chatGpt.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class QuestionRequestDto implements Serializable {
    private String question;
}