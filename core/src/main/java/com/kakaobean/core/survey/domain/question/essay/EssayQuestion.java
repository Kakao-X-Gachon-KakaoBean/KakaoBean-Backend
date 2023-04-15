package com.kakaobean.core.survey.domain.question.essay;

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


    public EssayQuestion(String title, String explanation, String questionNumber) {
        super(title, explanation, questionNumber);
    }

    public EssayQuestion(Survey survey, String title, String explanation, String questionNumber) {
        super(survey, title, explanation, questionNumber);
    }
}
