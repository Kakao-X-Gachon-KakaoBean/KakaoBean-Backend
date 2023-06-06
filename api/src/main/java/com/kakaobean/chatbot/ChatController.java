package com.kakaobean.chatbot;

import com.kakaobean.core.chatGpt.application.ChatGptService;
import com.kakaobean.core.chatGpt.application.dto.ChatGptResponseDto;
import com.kakaobean.chatbot.dto.ChatMessageDto;
import com.kakaobean.core.chatGpt.application.dto.QuestionRequestDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//import static com.kakaobean.chatbot.dto.ChatMessageDto.MessageType.CHAT;

//api
@RestController
public class ChatController {
    private final ChatGptService chatGptService;

    public ChatController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessageDto sendMessage(@Payload ChatMessageDto chatMessageDto) {
//        return chatMessageDto;
//    }

    @MessageMapping("/chat-gpt/question")
    @SendTo("/topic/public")
    public ChatMessageDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        ChatGptResponseDto responseFromGpt = chatGptService.askQuestion(requestDto);

        List<String> answer = responseFromGpt.getChoices()
                .stream().map(choice -> choice.getText()).collect(Collectors.toList());

        ChatMessageDto chatFromGpt = new ChatMessageDto();
        chatFromGpt.setContent(answer.toString());
//        chatFromGpt.setSender("ChatGpt");
//        chatFromGpt.setType(CHAT);

        return chatFromGpt;
    }

//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessageDto addUser(@Payload ChatMessageDto chatMessageDto, SimpMessageHeaderAccessor headerAccessor) {
//// Add username in web socket session
//        headerAccessor.getSessionAttributes().put("username", chatMessageDto.getSender());
//        return chatMessageDto;
//    }
}