package com.kakaobean.core.survey.application.dto;

import com.kakaobean.core.survey.application.dto.question.*;
import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.essay.EssayQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionFlowLogic;
import com.kakaobean.core.survey.domain.question.range.RangeQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GetSurveyResponseDto {

    private Long surveyId;
    private List<GetQuestionResponseDto> questions;

    public GetSurveyResponseDto(List<Question> questions, Long surveyId) {
        this.surveyId = surveyId;
        this.questions = questions.stream()
                .map(question -> question.toServiceDto())
                .collect(Collectors.toList());

    }
}
