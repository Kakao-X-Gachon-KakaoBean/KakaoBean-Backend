package com.kakaobean.core.survey.application.dto.response;

import com.kakaobean.core.survey.application.dto.request.question.GetQuestionResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GetSurveyResponseDto {

    private Long surveyId;
    private String surveyTitle;
    private List<GetQuestionResponseDto> questions;

    public GetSurveyResponseDto(String surveyTitle, List<Question> questions, Long surveyId) {
        this.surveyTitle = surveyTitle;
        this.surveyId = surveyId;
        this.questions = questions.stream()
                .map(question -> question.toServiceDto())
                .collect(Collectors.toList());

    }

    public static GetSurveyResponseDto from(Survey findSurvey) {
        return new GetSurveyResponseDto(findSurvey.getTitle(), findSurvey.getQuestions(), findSurvey.getId());
    }
}
