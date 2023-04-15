package com.kakaobean.survey.dto.request.question;

import com.kakaobean.core.survey.application.dto.question.RegisterQuestionFlowLogicRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterQuestionFlowLogicRequest {

    private List<String> conditionOfQuestionAnswers;
    private String nextQuestionNumber;

    public RegisterQuestionFlowLogicRequestDto toServiceDto() {
        return new RegisterQuestionFlowLogicRequestDto(
                conditionOfQuestionAnswers,
                nextQuestionNumber
        );
    }

    /**
     * 테스트 코드에서만 사용할 것.
     */
    @Builder
    public RegisterQuestionFlowLogicRequest(List<String> conditionOfQuestionAnswers, String nextQuestionNumber) {
        this.conditionOfQuestionAnswers = conditionOfQuestionAnswers;
        this.nextQuestionNumber = nextQuestionNumber;
    }
}
