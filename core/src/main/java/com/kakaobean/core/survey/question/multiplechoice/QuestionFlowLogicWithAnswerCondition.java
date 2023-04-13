package com.kakaobean.core.survey.question.multiplechoice;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 다대다 중간 테이블
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class QuestionFlowLogicWithAnswerCondition extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private QuestionFlowLogic logic;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MultipleChoiceQuestionAnswer answer;

    public QuestionFlowLogicWithAnswerCondition(QuestionFlowLogic logic, MultipleChoiceQuestionAnswer answer) {
        super(BaseStatus.ACTIVE);
        this.logic = logic;
        this.answer = answer;
    }
}
