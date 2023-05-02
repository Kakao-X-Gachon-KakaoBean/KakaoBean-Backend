package com.kakaobean.docs.extractor.survey.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.question.FindQuestionResponseDto;
import com.kakaobean.core.survey.application.dto.response.question.FindRangeQuestionResponseDto;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import com.kakaobean.survey.dto.request.question.RegisterQuestionRequest;
import com.kakaobean.survey.dto.request.question.RegisterRangeQuestionRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;

class RangeQuestionPayloadSubsectionExtractor implements PayloadSubsectionExtractor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {
        try {
            //List<RegisterRangeQuestionRequest> result = new ArrayList<>();
            FindSurveyResponseDto request = objectMapper.readValue(payload, FindSurveyResponseDto .class);
            FindRangeQuestionResponseDto result = null;
            for (FindQuestionResponseDto question : request.getQuestions()) {
                if(question.getClass() == FindRangeQuestionResponseDto.class ){
                    FindRangeQuestionResponseDto findQuestion = (FindRangeQuestionResponseDto) question;
                    //result.add(findQuestion);
                    result  = findQuestion;
                    break;
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
        return "questions";
    }

    @Override
    public PayloadSubsectionExtractor withSubsectionId(String subsectionId) {
        return null;
    }
}