package com.kakaobean.core.survey.domain.question.multiplechoice;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.exception.questionflowlogic.ExistSameConditionException;
import com.kakaobean.core.survey.exception.questionflowlogic.ExistSameLogicException;
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
public class MultipleChoiceQuestionFlowLogic extends BaseEntity {

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
    private List<MultipleChoiceQuestionFlowLogicCondition> conditions = new ArrayList<>();

    public MultipleChoiceQuestionFlowLogic(MultipleChoiceQuestion question, Question nextQuestion) {
        super(BaseStatus.ACTIVE);
        this.question = question;
        this.nextQuestion = nextQuestion;
    }

    public void addConditions(List<MultipleChoiceQuestionFlowLogicCondition> conditions){
        this.conditions.addAll(conditions);
    }

    public void compareWithOtherLogic(MultipleChoiceQuestionFlowLogic compareLogic) {
        if(compareConditions(compareLogic.getConditions())){
            if(this.nextQuestion == compareLogic.getNextQuestion()){
                throw new ExistSameLogicException();
            }
            throw new ExistSameConditionException();
        }
    }

    private boolean compareConditions(List<MultipleChoiceQuestionFlowLogicCondition> compareConditions) {
        if(conditions.size() != compareConditions.size()){
            return false;
        }
        return isSameConditions(compareConditions);
    }

    private boolean isSameConditions(List<MultipleChoiceQuestionFlowLogicCondition> compareConditions) {
        return extractAnswer(this.conditions).equals(extractAnswer(compareConditions)); //두 리스트의 내부 값이 동일한지 비교
    }

   private List<MultipleChoiceQuestionAnswer> extractAnswer(List<MultipleChoiceQuestionFlowLogicCondition> conditions){
        return conditions
                .stream()
                .map(condition -> condition.getAnswer())
                .collect(Collectors.toList());
    }
}

/**
 *
 * QuestionFlowLogic일 때는 jacoco가 인식을 못했는데
 * MultipleChoiceQuestionFlowLogic이라고 수정을 하면 jacoco가 인식을 함. 이는 추후에 살펴보자.
 *
 */