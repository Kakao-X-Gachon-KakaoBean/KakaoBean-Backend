package com.kakaobean.core.survey.question.essay;

import com.kakaobean.core.survey.Survey;
import com.kakaobean.core.survey.question.Question;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("essay_question")
public class EssayQuestion extends Question {
    /**
     * TODO 서술형 주관식은?
     */


    public EssayQuestion(Survey survey, String title, String explanation) {
        super(survey, title, explanation);
    }
}
