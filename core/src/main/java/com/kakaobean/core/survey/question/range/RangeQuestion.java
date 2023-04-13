package com.kakaobean.core.survey.question.range;

import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.survey.Survey;
import com.kakaobean.core.survey.question.Question;
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

    public RangeQuestion(Survey survey, String title, String explanation, Integer min, Integer max) {
        super(survey, title, explanation);
        this.min = min;
        this.max = max;
    }
}
