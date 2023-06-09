package com.kakaobean.docs.extractor.survey.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.question.FindMultipleChoiceQuestionResponseDto;
import com.kakaobean.core.survey.application.dto.response.question.FindQuestionResponseDto;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import com.kakaobean.survey.dto.request.question.RegisterMultipleChoiceQuestionRequest;
import com.kakaobean.survey.dto.request.question.RegisterQuestionRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;

public class MultipleChoiceQuestionPayloadSubsectionExtractor implements PayloadSubsectionExtractor {


    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {
        try {
            //List<RegisterRangeQuestionRequest> result = new ArrayList<>();
            FindSurveyResponseDto request = objectMapper.readValue(payload, FindSurveyResponseDto.class);
            FindMultipleChoiceQuestionResponseDto result = null;
            for (FindQuestionResponseDto question : request.getQuestions()) {
                if(question.getClass() == FindMultipleChoiceQuestionResponseDto.class){
                    FindMultipleChoiceQuestionResponseDto findQuestion = (FindMultipleChoiceQuestionResponseDto) question;
                    if(findQuestion.getLogics().size() > 0){
                        result  = findQuestion;
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
        return "questions";
    }

    @Override
    public PayloadSubsectionExtractor withSubsectionId(String subsectionId) {
        return null;
    }
}