package com.kakaobean.core.survey.domain;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.AccessLevel;
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
}
