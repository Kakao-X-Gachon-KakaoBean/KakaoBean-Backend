package com.kakaobean.core.survey.domain.question.multiplechoice;

import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 객관식 질문
 */

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("multiple_choice_question")
public class MultipleChoiceQuestion extends Question {

    /**
     * 객관식 답변(ex. 1번, 2번, 3번, 4번, 5번)
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<MultipleChoiceQuestionAnswer> answers = new ArrayList<>();

    /**
     * 다음 답변으로 넘어갈 조건을 담은 로직
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionFlowLogic> logics = new ArrayList<>();

    /**
     * 답을 몇개까지 고를지 제한선을 설정.그리고 이게 로직에서도 제한되어야 함.
     */
    private Integer numberOfAnswerChoices;

    public MultipleChoiceQuestion(
            String title, String explanation,
            String questionNumber,
            List<MultipleChoiceQuestionAnswer> answers,
            Integer numberOfAnswerChoices
    ) {
        super(title, explanation, questionNumber);
        this.answers = answers;
        this.numberOfAnswerChoices = numberOfAnswerChoices;
    }

    public void addLogics(List<QuestionFlowLogic> questionFlowLogics){
        this.logics.addAll(questionFlowLogics);
    }
}
