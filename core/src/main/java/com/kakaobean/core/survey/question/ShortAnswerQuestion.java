package com.kakaobean.core.survey.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("short_answer_question")
public class ShortAnswerQuestion extends Question{
    /**
     * TODO 단답 주관식은?
     */
}
