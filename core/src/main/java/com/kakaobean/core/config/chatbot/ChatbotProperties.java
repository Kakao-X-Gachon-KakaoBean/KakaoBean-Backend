package com.kakaobean.core.config.chatbot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "chatbot")
public class ChatbotProperties {

    private String apiUrl;
    private String mediaType;
    private Double topP;
    private Double temperature;
    private Integer maxToken;
    private String model;
    private String apiKey;
    private String bearer;
    private String authorization;
}
