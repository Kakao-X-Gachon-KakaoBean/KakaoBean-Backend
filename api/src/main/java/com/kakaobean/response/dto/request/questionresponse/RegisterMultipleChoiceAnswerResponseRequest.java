package com.kakaobean.response.dto.request.questionresponse;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterMultipleChoiceAnswerResponseRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class RegisterMultipleChoiceAnswerResponseRequest {

    @Positive
    private Long answerId;

    private String content;

    @Builder
    public RegisterMultipleChoiceAnswerResponseRequest(Long answerId, String content) {
        this.answerId = answerId;
        this.content = content;
    }

    public RegisterMultipleChoiceAnswerResponseRequestDto toServiceDto(){
        return new RegisterMultipleChoiceAnswerResponseRequestDto(
                answerId, content
        );
    }
}
