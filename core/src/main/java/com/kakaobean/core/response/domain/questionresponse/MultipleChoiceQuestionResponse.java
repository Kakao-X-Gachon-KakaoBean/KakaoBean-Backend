package com.kakaobean.core.response.domain.questionresponse;

import com.kakaobean.core.response.domain.SurveyResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("multiple_choice_question_response")
public class MultipleChoiceQuestionResponse extends QuestionResponse {

    private List<String> answer;

    public MultipleChoiceQuestionResponse(Long questionId, SurveyResponse surveyResponse, List<String> answer) {
        super(questionId, surveyResponse);
        this.answer = answer;
    }
    // 중복 선택
}

