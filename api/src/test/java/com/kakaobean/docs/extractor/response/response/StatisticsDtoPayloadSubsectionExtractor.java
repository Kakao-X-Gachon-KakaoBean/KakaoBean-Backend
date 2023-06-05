package com.kakaobean.docs.extractor.response.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.response.application.dto.response.FindSurveyStatisticsResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDtoPayloadSubsectionExtractor implements PayloadSubsectionExtractor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {
        try {
            FindSurveyStatisticsResponseDto response = objectMapper.readValue(payload, FindSurveyStatisticsResponseDto.class);
            return objectMapper.writeValueAsBytes(new Dto(
                    response.getSurveyId(),
                    response.getSurveyTitle(),
                    response.getSurveyDate(),
                    response.getNumberOfResponse(),
                    response.getCloseStatus()
            ));
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

    @Getter //이걸 붙여야함.
    @NoArgsConstructor
    static class Dto {

        private Long surveyId;

        private String surveyTitle;

        private String surveyDate;

        private Integer numberOfResponse;

        private Boolean closeStatus;

        private List<Object> surveyGenderPercent = new ArrayList<>();

        private List<Object> surveyAgePercent = new ArrayList<>();


        private List<Object> questionsResult = new ArrayList<>();


        public Dto(Long surveyId, String surveyTitle, String surveyDate, Integer numberOfResponse, Boolean closeStatus) {
            this.surveyId = surveyId;
            this.surveyTitle = surveyTitle;
            this.surveyDate = surveyDate;
            this.numberOfResponse = numberOfResponse;
            this.closeStatus = closeStatus;
        }
    }
}
