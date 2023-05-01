package com.kakaobean.core.survey.domain.question.essay;

import com.kakaobean.core.survey.application.dto.question.GetEssayQuestionResponseDto;
import com.kakaobean.core.survey.application.dto.question.GetQuestionResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("essay_question")
public class EssayQuestion extends Question {

    public EssayQuestion(String title, String explanation, String questionNumber, boolean finalQuestion) {
        super(title, explanation, questionNumber, finalQuestion);
    }

    @Override
    protected void detailValidate() {

    }

    @Override
    protected GetQuestionResponseDto createDetailServiceDto() {
        return new GetEssayQuestionResponseDto(
                getId(),
                getTitle(),
                getExplanation(),
                getQuestionNumber(),
                isFinalQuestion(),
                hasNextQuestion(getNextQuestion())
        );
    }
}
