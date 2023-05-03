package com.kakaobean.core.response.application.dto.request.questionresponse;

import com.kakaobean.core.response.domain.questionresponse.MultipleChoiceAnswerResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterMultipleChoiceAnswerResponseRequestDto {

    private Long answerId;
    private String content;

    public RegisterMultipleChoiceAnswerResponseRequestDto(Long answerId, String content) {
        this.answerId = answerId;
        this.content = content;
    }

    public MultipleChoiceAnswerResponse toEntity(){
        return new MultipleChoiceAnswerResponse(answerId, content);
    }
}
