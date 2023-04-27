package com.kakaobean.core.survey.domain.question.multiplechoice;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 객관식 답변은 다양한 로직에 포함된다.
 * 로직은 다양한 객관식 답변을 포함한다. 즉 다대다 관계다.
 * 해당 엔티티는 다대다 중간 테이블이다.
 *
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "question_flow_logic_with_answer_condition")
public class QuestionFlowLogicWithAnswerCondition extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 흐름을 수행하기 위한 로직 엔티티
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private QuestionFlowLogic logic;

    /**
     * 조건에 포함되는 객관식 답변 엔티티
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MultipleChoiceQuestionAnswer answer;

    public QuestionFlowLogicWithAnswerCondition(QuestionFlowLogic logic,
                                                MultipleChoiceQuestionAnswer answer) {
        super(BaseStatus.ACTIVE);
        this.logic = logic;
        this.answer = answer;
    }
}
