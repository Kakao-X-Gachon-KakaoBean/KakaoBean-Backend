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

    public MultipleChoiceQuestion(String title,
                                  String explanation,
                                  String questionNumber,
                                  List<MultipleChoiceQuestionAnswer> answers,
                                  Integer numberOfAnswerChoices,
                                  boolean finalQuestion) {
        super(title, explanation, questionNumber, finalQuestion);
        this.answers = answers;
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        answers.forEach(answer -> answer.addQuestion(this));
    }

    public void addLogics(List<QuestionFlowLogic> questionFlowLogics){
        this.logics.addAll(questionFlowLogics);
    }


    @Override
    protected void detailValidate() {
        validateLogicWithSameConditions();
        //validateNumberOfAnswersAssignedLogic();
    }

    //겹치는 로직이 있으면 안됨. (동일한 답변인데 다른 질문을 향하거나 동일한 로직이 2개)
    private void validateLogicWithSameConditions() {
        for (int i = 0; i < logics.size() - 1; i++) {
            QuestionFlowLogic standard = logics.get(i);
            for (int j = i + 1; j < logics.size(); j++) {
                standard.compareWithOtherLogic(logics.get(j));
            }
        }
    }


    //TODO 객관식에서 답변할 수 있는 개수와 로직이 가지는 조건 답변 개수가 같아야하는지 얘기를 해봐야함.
//    private void validateNumberOfAnswersAssignedLogic() {
//        for (QuestionFlowLogic logic : logics) {
//            if(logic.getConditions().size() != numberOfAnswerChoices ){
//                throw new RuntimeException("로직에 할당된 답변의 개수와 객관식에 설정된 답변의 개수가 다릅니다");
//            }
//        }
//    }
}
