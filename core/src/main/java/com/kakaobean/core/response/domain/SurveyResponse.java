package com.kakaobean.core.response.domain;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.response.domain.questionresponse.QuestionResponse;
import com.kakaobean.core.response.infrastructure.QuerydslResponsesDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Getter
@Entity(name = "survey_response")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyResponse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long surveyId;

    @Embedded
    private Respondent respondent;

    @OneToMany(mappedBy = "surveyResponse", cascade = CascadeType.ALL)
    private List<QuestionResponse> questionResponses = new ArrayList<>();

    public SurveyResponse(Long surveyId, Respondent respondent, List<QuestionResponse> questionResponses) {
        super(BaseStatus.ACTIVE);
        this.surveyId = surveyId;
        this.respondent = respondent;
        this.questionResponses = questionResponses;
        questionResponses.forEach(questionResponse -> questionResponse.addSurvey(this));
    }

    /**
     * 테스트에서만 사용할 것
     */
    @Builder
    public SurveyResponse(BaseStatus status,
                          Long id,
                          Long surveyId,
                          Respondent respondent,
                          List<QuestionResponse> questionResponses) {
        super(status);
        this.id = id;
        this.surveyId = surveyId;
        this.respondent = respondent;
        this.questionResponses = questionResponses;
    }
}
