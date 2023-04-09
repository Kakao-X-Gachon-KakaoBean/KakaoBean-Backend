package com.kakaobean.core.survey.question;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.survey.Survey;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //조인 전략과 여기만 바뀜
@DiscriminatorColumn(name = "question_type")
public abstract class Question extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Survey survey;

    private String questionTitle;
}
