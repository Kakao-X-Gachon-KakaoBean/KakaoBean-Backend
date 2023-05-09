package com.kakaobean.docs.extractor.response.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.response.dto.request.RegisterSurveyResponseRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurveyResponsePayloadSubsectionExtractorFactory {

    public static EssayQuestionResponsePayloadSubsectionExtractor getEssayQuestionResponseExtractor(){
        return new EssayQuestionResponsePayloadSubsectionExtractor();
    }

    public static RangeQuestionResponsePayloadSubsectionExtractor getRangeQuestionResponseExtractor(){
        return new RangeQuestionResponsePayloadSubsectionExtractor();
    }

    public static MultipleChoiceQuestionResponsePayloadSubsectionExtractor getMultipleChoiceQuestionResponseExtractor(){
        return new MultipleChoiceQuestionResponsePayloadSubsectionExtractor();
    }

    public static PayloadSubsectionExtractor getSurveyResponseExtractor(){

        ObjectMapper objectMapper = new ObjectMapper();

        return new PayloadSubsectionExtractor() {

            @Override
            public byte[] extractSubsection(byte[] payload, MediaType contentType) {
                try {
                    RegisterSurveyResponseRequest request = objectMapper.readValue(payload, RegisterSurveyResponseRequest.class);
                    return objectMapper.writeValueAsBytes(new SurveyResponsePayloadSubsectionExtractorFactory.Dto(request.getSurveyId()));
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

    @Getter
    @NoArgsConstructor
    static class Dto {
        private Long surveyId;
        private List<Object> questions = new ArrayList<>();
        public Dto(Long surveyId) {
            this.surveyId = surveyId;
        }
    }

}
