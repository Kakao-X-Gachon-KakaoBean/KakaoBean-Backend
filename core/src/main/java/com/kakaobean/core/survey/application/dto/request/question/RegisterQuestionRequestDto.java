package com.kakaobean.core.survey.application.dto.request.question;

import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public abstract class RegisterQuestionRequestDto {

    protected String title;
    protected String explanation;
    protected String questionNumber;
    protected Boolean finalQuestion;
    private String nextQuestionNumber;


    public RegisterQuestionRequestDto(String title,
                                      String explanation,
                                      String questionNumber,
                                      Boolean finalQuestion,
                                      String nextQuestionNumber) {
        this.title = title;
        this.explanation = explanation;
        this.questionNumber = questionNumber;
        this.finalQuestion = finalQuestion;
        this.nextQuestionNumber = nextQuestionNumber;
    }

    public Question toEntity(){
        return createdDetailQuestionEntity();
    }

    protected abstract Question createdDetailQuestionEntity();
    public abstract boolean hasQuestionFlowLogic();
}
