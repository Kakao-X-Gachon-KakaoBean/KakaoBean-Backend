package com.kakaobean.unit.controller.factory.chatbot;

import com.kakaobean.core.chatbot.application.dto.ChatGptResponseDto;
import com.kakaobean.core.chatbot.application.dto.Choice;

import java.time.LocalDate;
import java.util.List;

public class ChatGptResponseDtoFactory {

    private ChatGptResponseDtoFactory(){}

    public static ChatGptResponseDto create(){
        return new ChatGptResponseDto(
            "cmpl-7OqhMU9K4e5hAZXTFBnHUa1Waekem",
            "object=text_completion",
                LocalDate.now(),
                "text-davinci-003",
                List.of(new Choice(
                        "1. 학교의 교육과정은 어떻게 구성되어 있나요? " +
                                "2. 학교에서 제공하는 교육 장학금은 어떻게 신청하나요? " +
                                "3. 학교에서 제공하는 교육 프로그램은 무엇이 있나요?",
                        0,
                        "stop"
                ))
        );
    }
}
