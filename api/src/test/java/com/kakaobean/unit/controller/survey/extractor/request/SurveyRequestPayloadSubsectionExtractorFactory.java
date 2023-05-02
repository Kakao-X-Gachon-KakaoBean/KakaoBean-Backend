package com.kakaobean.unit.controller.survey.extractor.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurveyRequestPayloadSubsectionExtractorFactory {

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
                    RegisterSurveyRequest request = objectMapper.readValue(payload, RegisterSurveyRequest.class);
                    System.out.println(request.getSurveyTitle());
                    return objectMapper.writeValueAsBytes(new Dto(request.getSurveyTitle()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return new byte[0];
            }

            @Override
            public String getSubsectionId() {
                return "surveyTitle";
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
        private List<Object> questions = new ArrayList<>();
        public Dto(String title) {
            this.surveyTitle = title;
        }
    }
}