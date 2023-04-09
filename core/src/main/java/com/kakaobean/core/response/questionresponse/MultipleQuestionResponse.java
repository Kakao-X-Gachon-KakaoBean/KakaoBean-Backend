package com.kakaobean.core.response.questionresponse;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("multiple_question_response")
public class MultipleQuestionResponse extends QuestionResponse {
    /**
     * 객관식
     */
}
