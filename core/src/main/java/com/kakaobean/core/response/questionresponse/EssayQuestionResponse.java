package com.kakaobean.core.response.questionresponse;

import com.kakaobean.core.survey.question.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("essay_question_response")
public class EssayQuestionResponse extends QuestionResponse {

    private String answer;

    public EssayQuestionResponse(String answer) {
        this.answer = answer;
    }
}
