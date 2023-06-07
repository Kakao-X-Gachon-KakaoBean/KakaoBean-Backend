package com.kakaobean.core.chatbot.application;

import com.kakaobean.core.chatbot.application.dto.ChatGptRequestDto;
import com.kakaobean.core.chatbot.application.dto.ChatGptResponseDto;
import com.kakaobean.core.chatbot.application.dto.QuestionRequestDto;

import com.kakaobean.core.config.chatbot.ChatbotProperties;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private RestTemplate restTemplate = new RestTemplate();
    private final ChatbotProperties chatbotProperties;

    public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequestDto(
                                chatbotProperties.getModel(),
                                requestDto.getQuestion(),
                                chatbotProperties.getMaxToken(),
                                chatbotProperties.getTemperature(),
                                chatbotProperties.getTopP()
                        )
                )
        );
    }

    private ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate
                .postForEntity(
                        chatbotProperties.getApiUrl(),
                        chatGptRequestDtoHttpEntity,
                        ChatGptResponseDto.class
                );

        ChatGptResponseDto body = responseEntity.getBody();
        return body;
    }

    private HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(chatbotProperties.getMediaType()));
        headers.add(chatbotProperties.getAuthorization(), chatbotProperties.getBearer() + " " + chatbotProperties.getApiKey());
        return new HttpEntity<>(requestDto, headers);
    }
}
