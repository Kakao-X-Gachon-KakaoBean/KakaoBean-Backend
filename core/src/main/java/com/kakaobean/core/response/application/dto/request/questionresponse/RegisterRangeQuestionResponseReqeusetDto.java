package com.kakaobean.core.response.application.dto.request.questionresponse;

import com.kakaobean.core.response.domain.questionresponse.QuestionResponse;
import com.kakaobean.core.response.domain.questionresponse.RangeQuestionResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRangeQuestionResponseReqeusetDto extends RegisterQuestionResponseRequestDto{

    private Integer answerValue;

    public RegisterRangeQuestionResponseReqeusetDto(Long questionId, Integer answerValue) {
        super(questionId);
        this.answerValue = answerValue;
    }

    @Override
    protected QuestionResponse createdDetailQuestionResponseEntity() {
        return new RangeQuestionResponse(questionId, answerValue);
    }
}
