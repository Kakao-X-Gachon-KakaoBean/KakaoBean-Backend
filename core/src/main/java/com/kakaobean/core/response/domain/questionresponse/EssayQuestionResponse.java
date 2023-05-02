package com.kakaobean.core.response.domain.questionresponse;

import com.kakaobean.core.response.domain.SurveyResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("essay_question_response")
public class EssayQuestionResponse extends QuestionResponse {

    private String answer;

    public EssayQuestionResponse(Long questionId, SurveyResponse surveyResponse, String answer) {
        super(questionId, surveyResponse);
        this.answer = answer;
    }

}
