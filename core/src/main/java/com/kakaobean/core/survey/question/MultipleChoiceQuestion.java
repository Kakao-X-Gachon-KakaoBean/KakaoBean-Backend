package com.kakaobean.core.survey.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("multiple_choice_question")
public class MultipleChoiceQuestion extends Question{
    /**
     *  TODO 객관식 답들이 들어감.
     */
}
