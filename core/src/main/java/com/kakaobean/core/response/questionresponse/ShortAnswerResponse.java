package com.kakaobean.core.response.questionresponse;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("short_answer_question_response")
public class ShortAnswerResponse extends QuestionResponse {
    /**
     * 객관식
     */
}
