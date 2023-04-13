package com.kakaobean.core.survey.question.multiplechoice;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MultipleChoiceQuestionAnswer extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MultipleChoiceQuestion question;

    public MultipleChoiceQuestionAnswer(String content, MultipleChoiceQuestion question) {
        super(BaseStatus.ACTIVE);
        this.content = content;
        this.question = question;
    }
}
