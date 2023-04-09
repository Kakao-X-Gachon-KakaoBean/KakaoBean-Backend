package com.kakaobean.core.survey.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("easy_question")
public class EssayQuestion extends Question{
    /**
     * TODO 서술형 주관식은?
     */
}
