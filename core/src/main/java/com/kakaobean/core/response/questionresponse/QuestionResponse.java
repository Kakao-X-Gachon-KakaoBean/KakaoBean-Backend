package com.kakaobean.core.response.questionresponse;

import com.kakaobean.core.response.SurveyResponse;
import com.kakaobean.core.survey.question.Question;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //조인 전략과 여기만 바뀜
@DiscriminatorColumn(name = "question_type")
public abstract class QuestionResponse {

    @Id
    @GeneratedValue
    private Long id;

    private String questionTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private SurveyResponse surveyResponse;
}
