package com.kakaobean.core.survey.domain.question.multiplechoice;

import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 객관식 질문
 */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("multiple_choice_question")
public class MultipleChoiceQuestion extends Question {

    /**
     * 객관식 답변(ex. 1번, 2번, 3번, 4번, 5번)
     */
    @OneToMany(mappedBy = "question")
    private List<MultipleChoiceQuestionAnswer> answers = new ArrayList<>();

    /**
     * 다음 답변으로 넘어갈 조건을 담은 로직
     */
    @OneToMany(mappedBy = "question")
    private List<QuestionFlowLogic> logics = new ArrayList<>();

    public MultipleChoiceQuestion(Survey survey, String title, String explanation) {
        super(survey, title, explanation);
    }
}
