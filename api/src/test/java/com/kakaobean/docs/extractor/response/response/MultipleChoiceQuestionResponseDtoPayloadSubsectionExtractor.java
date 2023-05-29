package com.kakaobean.docs.extractor.response.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.response.application.dto.response.FindResponsesDto;
import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.response.application.dto.response.question.EssayQuestionResponseDto;
import com.kakaobean.core.response.application.dto.response.question.MultipleChoiceQuestionResponseDto;
import com.kakaobean.core.response.application.dto.response.question.QuestionResponseDto;
import com.kakaobean.response.dto.request.RegisterSurveyResponseRequest;
import com.kakaobean.response.dto.request.questionresponse.RegisterMultipleChoiceQuestionResponseRequest;
import com.kakaobean.response.dto.request.questionresponse.RegisterQuestionResponseRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;

public class MultipleChoiceQuestionResponseDtoPayloadSubsectionExtractor implements PayloadSubsectionExtractor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {

        try {
            FindResponsesDto findResponsesDto = objectMapper.readValue(payload, FindResponsesDto.class);
            MultipleChoiceQuestionResponseDto result = null;
            for (SurveyResponseDto response : findResponsesDto.getResponses()) {
                for (QuestionResponseDto questionResponse : response.getQuestionResponses()) {
                    if(questionResponse.getClass() == MultipleChoiceQuestionResponseDto.class){
                        result = (MultipleChoiceQuestionResponseDto) questionResponse;
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
