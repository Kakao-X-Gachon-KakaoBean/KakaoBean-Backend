package com.kakaobean.core.response.domain.questionresponse;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("multiple_choice_question_response")
public class MultipleChoiceQuestionResponse extends QuestionResponse {

    @OneToMany(mappedBy = "multipleChoiceQuestionResponse", cascade = CascadeType.ALL)
    private List<MultipleChoiceAnswerResponse> answerResponses = new ArrayList<>();

    public MultipleChoiceQuestionResponse(Long questionId,
                                          List<MultipleChoiceAnswerResponse> answerResponses) {
        super(questionId);
        this.answerResponses = answerResponses;
        answerResponses.forEach(answerResponse -> answerResponse.addMultipleChoiceQuestion(this));
    }
}

