package com.kakaobean.docs.extractor.response.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.response.application.dto.response.FindResponsesDto;
import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.response.application.dto.response.question.EssayQuestionResponseDto;
import com.kakaobean.core.response.application.dto.response.question.QuestionResponseDto;

import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;

public class EssayQuestionResponseDtoPayloadSubsectionExtractor implements PayloadSubsectionExtractor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {

        try {
            FindResponsesDto findResponsesDto = objectMapper.readValue(payload, FindResponsesDto.class);
            EssayQuestionResponseDto result = null;
            for (SurveyResponseDto response : findResponsesDto.getResponses()) {
                for (QuestionResponseDto questionResponse : response.getQuestionResponses()) {
                    if(questionResponse.getClass() == EssayQuestionResponseDto.class){
                        result = (EssayQuestionResponseDto) questionResponse;
                        break;
                    }
                }
            }
            return objectMapper.writeValueAsBytes(result);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String getSubsectionId() {
        return null;
    }

    @Override
    public PayloadSubsectionExtractor withSubsectionId(String subsectionId) {
        return null;
    }
}
