package com.kakaobean.core.survey.domain.question.range;

import com.kakaobean.core.survey.application.dto.question.GetQuestionResponseDto;
import com.kakaobean.core.survey.application.dto.question.GetRangeQuestionResponseDto;
import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.exception.RangeQuestionBoundaryValueException;
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

    public RangeQuestion(
            String title,
            String explanation,
            String questionNumber,
            Integer min,
            Integer max,
            boolean finalQuestion
    ) {
        super(title, explanation, questionNumber, finalQuestion);
        this.min = min;
        this.max = max;
    }


    @Override
    protected void detailValidate() {
        if(min == max) throw new RangeQuestionBoundaryValueException();
    }

    @Override
    protected GetQuestionResponseDto createDetailServiceDto() {
        return new GetRangeQuestionResponseDto(
                getId(),
                getTitle(),
                getExplanation(),
                getQuestionNumber(),
                isFinalQuestion(),
                hasNextQuestion(getNextQuestion()),
                getMin(),
                getMax()
        );
    }
}
