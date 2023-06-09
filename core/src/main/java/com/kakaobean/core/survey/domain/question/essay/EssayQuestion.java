package com.kakaobean.core.survey.domain.question.essay;

import com.kakaobean.core.survey.application.dto.response.question.FindEssayQuestionResponseDto;
import com.kakaobean.core.survey.application.dto.response.question.FindQuestionResponseDto;
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
    protected FindQuestionResponseDto createDetailServiceDto() {
        return new FindEssayQuestionResponseDto(
                getId(),
                getTitle(),
                getExplanation(),
                getQuestionNumber(),
                isFinalQuestion(),
                hasNextQuestion(getNextQuestion())
        );
    }
}
