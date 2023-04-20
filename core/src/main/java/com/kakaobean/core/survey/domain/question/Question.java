package com.kakaobean.core.survey.domain.question;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.survey.domain.Survey;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private boolean finalQuestion;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Question nextQuestion;

    @OneToMany(mappedBy =  "nextQuestion")
    private List<Question> previousQuestions = new ArrayList<>();

    public Question(String title, String explanation, String questionNumber, boolean finalQuestion) {
        super(BaseStatus.ACTIVE);
        this.title = title;
        this.explanation = explanation;
        this.questionNumber = questionNumber;
        this.finalQuestion = finalQuestion;
    }

    public void addSurvey(Survey survey) {
        this.survey = survey;
    }
}

/**
 * 다음 문제도 넣어야함
 */
