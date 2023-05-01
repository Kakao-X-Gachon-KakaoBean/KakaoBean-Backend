package com.kakaobean.core.survey.domain;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private SurveyOwner surveyOwner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    private String title;

    public Survey(String title,
                  SurveyOwner surveyOwner,
                  List<Question> questions) {
        super(BaseStatus.ACTIVE);
        this.surveyOwner = surveyOwner;
        this.questions.addAll(questions);
        this.title = title;
        questions.forEach(question -> question.addSurvey(this));
    }

    @Builder
    public Survey(Long id,
                  SurveyOwner surveyOwner,
                  List<Question> questions,
                  String title) {
        super(BaseStatus.ACTIVE);
        this.id = id;
        this.surveyOwner = surveyOwner;
        this.questions = questions;
        this.title = title;
    }

    public void place(SurveyValidator surveyValidator) {
        surveyValidator.validate(this);
    }
}
