package com.kakaobean.survey.dto.request;

import com.kakaobean.survey.dto.request.question.RegisterQuestionRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//https://stackoverflow.com/questions/42965921/use-interface-in-spring-controller-method-argument

@Getter
@NoArgsConstructor
public class RegisterSurveyRequest {

    private List<RegisterQuestionRequest> questions = new ArrayList<>();

    /**
     * 테스트 코드에서만 사용할 것.
     */
    public RegisterSurveyRequest(List<RegisterQuestionRequest> questions) {
        this.questions = questions;
    }
}
