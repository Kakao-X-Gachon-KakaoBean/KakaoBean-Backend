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
@DiscriminatorValue("range_question_response")
public class RangeQuestionResponse extends QuestionResponse{

    private Integer answerValue;

    public RangeQuestionResponse(Long questionId, Integer value) {
        super(questionId);
        this.answerValue = value;
    }
}
