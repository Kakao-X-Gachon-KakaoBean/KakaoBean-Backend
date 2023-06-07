package com.kakaobean.chatbot;

import com.kakaobean.core.chatbot.application.ChatbotService;
import com.kakaobean.core.chatbot.application.dto.ChatGptResponseDto;
import com.kakaobean.chatbot.dto.ChatMessageDto;
import com.kakaobean.core.chatbot.application.dto.Choice;
import com.kakaobean.core.chatbot.application.dto.QuestionRequestDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ChatController {
    private final ChatbotService chatGptService;

    public ChatController(ChatbotService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @MessageMapping("/chat-gpt/question")
    @SendTo("/topic/public")
    public ResponseEntity sendQuestion(@RequestBody QuestionRequestDto requestDto) {

        ChatGptResponseDto responseFromGpt = chatGptService.askQuestion(requestDto);
        System.out.println("responseFromGpt = " + responseFromGpt.toString());
        List<String> answer = responseFromGpt
                .getChoices()
                .stream()
                .map(Choice::getText)
                .collect(Collectors.toList());

        return new ResponseEntity(new ChatMessageDto(answer.toString()), HttpStatus.OK);
    }
}