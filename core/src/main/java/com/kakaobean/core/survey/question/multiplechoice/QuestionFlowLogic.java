package com.kakaobean.core.survey.question.multiplechoice;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class QuestionFlowLogic extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MultipleChoiceQuestion question;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MultipleChoiceQuestion nextQuestion;

    @OneToMany(mappedBy = "logic", cascade = CascadeType.ALL)
    private List<QuestionFlowLogicWithAnswerCondition> conditions = new ArrayList<>();

    public QuestionFlowLogic(MultipleChoiceQuestion question, MultipleChoiceQuestion nextQuestion) {
        super(BaseStatus.ACTIVE);
        this.question = question;
        this.nextQuestion = nextQuestion;
    }
}
