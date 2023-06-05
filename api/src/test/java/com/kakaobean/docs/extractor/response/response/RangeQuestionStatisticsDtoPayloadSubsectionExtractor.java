package com.kakaobean.docs.extractor.response.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaobean.core.response.application.dto.response.FindSurveyStatisticsResponseDto;
import com.kakaobean.core.response.application.dto.response.statistics.QuestionStatisticsDto;
import com.kakaobean.core.response.application.dto.response.statistics.RangeQuestionStatisticsDto;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.io.IOException;

public class RangeQuestionStatisticsDtoPayloadSubsectionExtractor implements PayloadSubsectionExtractor {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] extractSubsection(byte[] payload, MediaType contentType) {

        try {
            FindSurveyStatisticsResponseDto findSurveyStatisticsResponseDto = objectMapper.readValue(payload, FindSurveyStatisticsResponseDto.class);
            RangeQuestionStatisticsDto result = null;
            for (QuestionStatisticsDto questionStatistics : findSurveyStatisticsResponseDto.getQuestionsResult()) {
                if (questionStatistics.getClass() == RangeQuestionStatisticsDto.class) {
                    result = (RangeQuestionStatisticsDto) questionStatistics;
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
