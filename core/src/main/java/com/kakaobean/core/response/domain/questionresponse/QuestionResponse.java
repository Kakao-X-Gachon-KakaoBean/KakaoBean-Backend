package com.kakaobean.core.response.domain.questionresponse;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.response.domain.SurveyResponse;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "question_response")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //조인 전략과 여기만 바뀜
@DiscriminatorColumn(name = "question_type")
public abstract class QuestionResponse extends BaseEntity {

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

    public void addSurveyId(SurveyResponse surveyResponse){
        this.surveyResponse = surveyResponse;
    }
}
