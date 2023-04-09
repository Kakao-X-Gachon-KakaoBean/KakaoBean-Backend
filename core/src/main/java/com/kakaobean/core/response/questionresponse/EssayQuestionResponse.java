package com.kakaobean.core.response.questionresponse;

import com.kakaobean.core.survey.question.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("easy_question_response")
public class EssayQuestionResponse extends QuestionResponse {
    /**
     * TODO 서술형 주관식은?
     */
}
