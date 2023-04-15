package com.kakaobean.core.survey.domain.question.range;

import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("range_question")
public class RangeQuestion extends Question {

    private Integer min;
    private Integer max;

    public RangeQuestion(Survey survey, String title, String explanation, String questionNumber, Integer min, Integer max) {
        super(survey, title, explanation, questionNumber);
        this.min = min;
        this.max = max;
    }
}
