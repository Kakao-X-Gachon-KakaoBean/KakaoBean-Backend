package com.kakaobean.survey.dto.request.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterQuestionFlowLogicRequest {

    private List<String> conditionOfQuestionNumbers;
    private String nextQuestionNumber;

    /**
     * 테스트 코드에서만 사용할 것.
     */
    @Builder
    public RegisterQuestionFlowLogicRequest(List<String> conditionOfQuestionNumbers, String nextQuestionNumber) {
        this.conditionOfQuestionNumbers = conditionOfQuestionNumbers;
        this.nextQuestionNumber = nextQuestionNumber;
    }
}
