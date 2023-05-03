package com.kakaobean.response.dto.request;

import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;
import com.kakaobean.response.dto.request.questionresponse.RegisterQuestionResponseRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RegisterSurveyResponseRequest {

    @NotBlank
    private Long surveyId;

    @NotEmpty
    private List<RegisterQuestionResponseRequest> questions = new ArrayList<>();

    public RegisterSurveyResponseRequest(Long surveyId, List<RegisterQuestionResponseRequest> questions) {
        this.surveyId = surveyId;
        this.questions = questions;
    }

    public RegisterSurveyResponseRequestDto toServiceDto(Long memberId){
        return new RegisterSurveyResponseRequestDto(
                memberId,
                surveyId,
                questions.stream()
                        .map(question -> question.toServiceDto())
                        .collect(Collectors.toList())
        );

    }
}
