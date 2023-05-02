package com.kakaobean.docs.extractor.survey.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurveyResponsePayloadSubsectionExtractorFactory {

    public static MultipleChoiceQuestionPayloadSubsectionExtractor getMultipleChoiceQuestionExtractor(){
        return new MultipleChoiceQuestionPayloadSubsectionExtractor();
    }

    public static RangeQuestionPayloadSubsectionExtractor getRangeQuestionExtractor(){
        return new RangeQuestionPayloadSubsectionExtractor();
    }

    public static EssayQuestionPayloadSubsectionExtractor getEssayQuestionExtractor(){
        return new EssayQuestionPayloadSubsectionExtractor();
    }

    public static PayloadSubsectionExtractor getSurveyExtractor(){

        ObjectMapper objectMapper = new ObjectMapper();

        return new PayloadSubsectionExtractor() {

            @Override
            public byte[] extractSubsection(byte[] payload, MediaType contentType) {
                try {
                    FindSurveyResponseDto response = objectMapper.readValue(payload, FindSurveyResponseDto.class);
                    return objectMapper.writeValueAsBytes(new Dto(response.getSurveyTitle(), response.getSurveyId()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return new byte[0];
            }

            @Override
            public String getSubsectionId() {
                return "";
            }

            @Override
            public PayloadSubsectionExtractor withSubsectionId(String subsectionId) {
                return null;
            }
        };
    }

    @Getter //이걸 붙여야함.
    @NoArgsConstructor
    static class Dto {

        private String surveyTitle;
        private Long surveyId;

        private List<Object> questions = new ArrayList<>();

        public Dto(String surveyTitle, Long surveyId) {
            this.surveyTitle = surveyTitle;
            this.surveyId = surveyId;
        }
    }
}