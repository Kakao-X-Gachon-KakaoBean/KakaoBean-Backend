package com.kakaobean.response.dto.request.questionresponse;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterQuestionResponseRequestDto;
import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterRangeQuestionResponseReqeusetDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRangeQuestionResponseRequest extends RegisterQuestionResponseRequest{

    private Integer answers;

    @Builder
    public RegisterRangeQuestionResponseRequest(QuestionDTOType type, Long questionId, Integer answers) {
        super(type, questionId);
        this.answers = answers;
    }

    @Override
    protected RegisterQuestionResponseRequestDto createDetailServiceDto() {
        return new RegisterRangeQuestionResponseReqeusetDto(
                questionId, answers
        );
    }
}
