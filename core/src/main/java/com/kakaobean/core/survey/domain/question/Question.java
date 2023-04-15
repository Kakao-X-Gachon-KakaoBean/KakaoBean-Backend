package com.kakaobean.core.survey.domain.question;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.survey.domain.Survey;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //조인 전략과 여기만 바뀜
@DiscriminatorColumn(name = "question_type")
public abstract class Question extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Survey survey;

    private String title;

    private String explanation;

    //설문내의 고유한 번호를 추가해야함.
    private String questionNumber;


    public Question(String title, String explanation, String questionNumber) {
        super(BaseStatus.ACTIVE);
        this.title = title;
        this.explanation = explanation;
        this.questionNumber = questionNumber;
    }

    public Question(Survey survey, String title, String explanation, String questionNumber) {
        super(BaseStatus.ACTIVE);
        this.survey = survey;
        this.title = title;
        this.explanation = explanation;
        this.questionNumber = questionNumber;
    }
}

/**
 * 제출 문제인지, 다음 문제도 넣어야함
 */
