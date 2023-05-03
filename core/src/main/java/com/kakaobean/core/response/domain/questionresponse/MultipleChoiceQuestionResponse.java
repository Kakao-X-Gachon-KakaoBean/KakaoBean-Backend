package com.kakaobean.core.response.domain.questionresponse;

import com.kakaobean.core.response.domain.SurveyResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("multiple_choice_question_response")
public class MultipleChoiceQuestionResponse extends QuestionResponse {

    @OneToMany(mappedBy = "multipleChoiceQuestion", cascade = CascadeType.ALL)
    private List<MultipleChoiceAnswerResponse> answerResponses;

    public MultipleChoiceQuestionResponse(Long questionId,
                                          List<MultipleChoiceAnswerResponse> answerResponses) {
        super(questionId);
        this.answerResponses = answerResponses;
        answerResponses.forEach(answerResponse -> answerResponse.addMultipleChoiceQuestion(this));
    }
}

