package com.kakaobean.core.survey.application.dto.response;

import com.kakaobean.core.survey.application.dto.response.question.FindQuestionResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class FindSurveyResponseDto {

    private Long surveyId;
    private String surveyTitle;
    private List<FindQuestionResponseDto> questions;

    public FindSurveyResponseDto(String surveyTitle, List<Question> questions, Long surveyId) {
        this.surveyTitle = surveyTitle;
        this.surveyId = surveyId;
        this.questions = questions.stream()
                .map(question -> question.toServiceDto())
                .collect(Collectors.toList());

    }

    public static FindSurveyResponseDto from(Survey findSurvey) {
        return new FindSurveyResponseDto(findSurvey.getTitle(), findSurvey.getQuestions(), findSurvey.getId());
    }

    @Builder
    public FindSurveyResponseDto(Long surveyId, String surveyTitle, List<FindQuestionResponseDto> questions) {
        this.surveyId = surveyId;
        this.surveyTitle = surveyTitle;
        this.questions = questions;
    }
}
