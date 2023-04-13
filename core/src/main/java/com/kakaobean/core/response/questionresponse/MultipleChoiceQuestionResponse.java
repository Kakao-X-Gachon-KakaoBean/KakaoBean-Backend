package com.kakaobean.core.response.questionresponse;

import com.kakaobean.core.response.SurveyResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("multiple_choice_question_response")
public class MultipleChoiceQuestionResponse extends QuestionResponse {

    private String answer;

    public MultipleChoiceQuestionResponse(Long questionId, SurveyResponse surveyResponse, String answer) {
        super(questionId, surveyResponse);
        this.answer = answer;
    }
}

