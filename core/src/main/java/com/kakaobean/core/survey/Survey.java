package com.kakaobean.core.survey;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.survey.question.Question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Survey extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private SurveyOwner surveyOwner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "survey")
    List<Question> questions = new ArrayList<>();

}
