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
public class MultipleChoiceQuestionFlowLogicCondition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 흐름을 수행하기 위한 로직 엔티티
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MultipleChoiceQuestionFlowLogic logic;

    /**
     * 조건에 포함되는 객관식 답변 엔티티
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MultipleChoiceQuestionAnswer answer;

    public MultipleChoiceQuestionFlowLogicCondition(MultipleChoiceQuestionFlowLogic logic,
                                                    MultipleChoiceQuestionAnswer answer) {
        super(BaseStatus.ACTIVE);
        this.logic = logic;
        this.answer = answer;
    }
}

/**
 * 이것도 마찬가지 QuestionFlowLogicWithAnsewrCondition가 클래스 이름일 때는 jacoco가 인식을 못함.
 * 근데 MultipleChoiceQuestionFlowLogicCondition로 변경하니까 jacoco가 인식하기 시작함.(?!)
 */