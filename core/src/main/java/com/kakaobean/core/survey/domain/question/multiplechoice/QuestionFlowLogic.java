package com.kakaobean.core.survey.domain.question.multiplechoice;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 객관식 질문에서 다음 질문으로 넘어가기 위해 설정할 로직 엔티티
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "question_flow_logic")
public class QuestionFlowLogic extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 로직이 포함되어 있는 객관식 질문
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MultipleChoiceQuestion question;

    /**
     * 로직의 조건과 동일하다면 이동할 다음 질문
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Question nextQuestion;

    /**
     * 로직을 수행하기 위한 검증 조건들.
     * 조건은 '어떤 객관식의 답을 골랐는가'이다.
     */
    @OneToMany(mappedBy = "logic", cascade = CascadeType.ALL)
    private List<QuestionFlowLogicWithAnswerCondition> conditions = new ArrayList<>();

    public QuestionFlowLogic(MultipleChoiceQuestion question, Question nextQuestion) {
        super(BaseStatus.ACTIVE);
        this.question = question;
        this.nextQuestion = nextQuestion;
    }

    public void addConditions(List<QuestionFlowLogicWithAnswerCondition> conditions){
        this.conditions.addAll(conditions);
    }

    public void compareWithOtherLogic(QuestionFlowLogic compareLogic) {
        if(compareConditions(compareLogic.getConditions())){
            if(nextQuestion == compareLogic.getNextQuestion()){
                throw new RuntimeException("로직에서 중복되는 조건이 있습니다.");
            }
            else {
                throw new RuntimeException("동일한 중복 로직이 있습니다");
            }
        }
    }

    private boolean compareConditions(List<QuestionFlowLogicWithAnswerCondition> compareConditions) {
        if(conditions.size() != compareConditions.size()){
            return false;
        }

        if(!isSameConditions(compareConditions)){
            return false;
        }

        return true;
    }

    private boolean isSameConditions(List<QuestionFlowLogicWithAnswerCondition> compareConditions) {
        return extractAnswer(this.conditions).equals(extractAnswer(compareConditions));
    }

    private List<MultipleChoiceQuestionAnswer> extractAnswer(List<QuestionFlowLogicWithAnswerCondition> conditions){
        return conditions.stream().map(condition -> condition.getAnswer()).collect(Collectors.toList());
    }
}
