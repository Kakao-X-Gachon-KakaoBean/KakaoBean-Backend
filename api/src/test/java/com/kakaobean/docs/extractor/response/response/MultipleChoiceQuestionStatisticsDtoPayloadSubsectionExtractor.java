package com.kakaobean.docs.extractor.response.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.response.application.dto.response.FindSurveyStatisticsResponseDto;
import com.kakaobean.core.response.application.dto.response.statistics.MultipleQuestionStatisticsDto;
import com.kakaobean.core.response.application.dto.response.statistics.QuestionStatisticsDto;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;

public class MultipleChoiceQuestionStatisticsDtoPayloadSubsectionExtractor implements PayloadSubsectionExtractor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {

        try {
            FindSurveyStatisticsResponseDto findSurveyStatisticsResponseDto = objectMapper.readValue(payload, FindSurveyStatisticsResponseDto.class);
            MultipleQuestionStatisticsDto result = null;
            for (QuestionStatisticsDto questionStatistics : findSurveyStatisticsResponseDto.getQuestionsResult()) {
                if (questionStatistics.getClass() == MultipleQuestionStatisticsDto.class) {
                    result = (MultipleQuestionStatisticsDto) questionStatistics;
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
        return null;
    }

    @Override
    public PayloadSubsectionExtractor withSubsectionId(String subsectionId) {
        return null;
    }
}
