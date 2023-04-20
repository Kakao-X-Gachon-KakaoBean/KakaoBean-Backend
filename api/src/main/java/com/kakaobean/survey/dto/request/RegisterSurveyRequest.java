package com.kakaobean.survey.dto.request;

import com.kakaobean.core.survey.application.dto.RegisterSurveyRequestDto;
import com.kakaobean.survey.dto.request.question.RegisterQuestionRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//https://stackoverflow.com/questions/42965921/use-interface-in-spring-controller-method-argument

@Getter
@NoArgsConstructor
public class RegisterSurveyRequest {

    @NotEmpty
    private List<RegisterQuestionRequest> questions = new ArrayList<>();

    public RegisterSurveyRequestDto toServiceDto(Long memberId) {
        return new RegisterSurveyRequestDto(
                memberId,
                questions.stream()
                        .map(RegisterQuestionRequest::toServiceDto)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 테스트 코드에서만 사용할 것.
     */
    public RegisterSurveyRequest(List<RegisterQuestionRequest> questions) {
        this.questions = questions;
    }
}
