package com.kakaobean.core.survey.application.dto.question;

import ch.qos.logback.core.joran.conditional.Condition;
import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import com.kakaobean.core.survey.domain.question.multiplechoice.QuestionFlowLogic;
import com.kakaobean.core.survey.domain.question.multiplechoice.QuestionFlowLogicWithAnswerCondition;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RegisterQuestionFlowLogicRequestDto {

    private List<String> conditionOfQuestionAnswers;
    private String nextQuestionNumber;

    @Builder
    public RegisterQuestionFlowLogicRequestDto(List<String> conditionOfQuestionAnswers, String nextQuestionNumber) {
        this.conditionOfQuestionAnswers = conditionOfQuestionAnswers;
        this.nextQuestionNumber = nextQuestionNumber;
    }
}
