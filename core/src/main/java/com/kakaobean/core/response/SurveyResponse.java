package com.kakaobean.core.response;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.response.questionresponse.QuestionResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SurveyResponse extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long surveyId;

    @Embedded
    private Respondent respondent;

    @OneToMany(mappedBy = "surveyResponse")
    private List<QuestionResponse> questionResponses = new ArrayList<>();


}
