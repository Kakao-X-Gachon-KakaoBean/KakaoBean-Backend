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
@DiscriminatorValue("range_question_response")
public class RangeQuestionResponse extends QuestionResponse{

    private Integer answerValue;

    public RangeQuestionResponse(Long questionId, SurveyResponse surveyResponse, Integer value) {
        super(questionId, surveyResponse);
        this.answerValue = value;
    }
}
