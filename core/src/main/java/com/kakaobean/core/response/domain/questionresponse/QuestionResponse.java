package com.kakaobean.core.response.domain.questionresponse;

import com.kakaobean.core.response.domain.SurveyResponse;
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

    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private SurveyResponse surveyResponse;

    public QuestionResponse(Long questionId, SurveyResponse surveyResponse) {
        this.questionId = questionId;
        this.surveyResponse = surveyResponse;
    }
}
