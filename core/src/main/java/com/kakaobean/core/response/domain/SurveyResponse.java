package com.kakaobean.core.response.domain;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.response.domain.questionresponse.QuestionResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Entity(name = "survey_response")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyResponse extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long surveyId;

    @Embedded
    private Respondent respondent;

    @OneToMany(mappedBy = "surveyResponse")
    private List<QuestionResponse> questionResponses = new ArrayList<>();

    public SurveyResponse(Long id, Long surveyId, Respondent respondent, List<QuestionResponse> questionResponses) {
        super(BaseStatus.ACTIVE);
        this.surveyId = surveyId;
        this.respondent = respondent;
        this.questionResponses = questionResponses;
        questionResponses.forEach(questionResponse -> questionResponse.addSurveyId(this));
    }

}
