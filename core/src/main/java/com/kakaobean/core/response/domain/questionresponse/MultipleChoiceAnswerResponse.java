package com.kakaobean.core.response.domain.questionresponse;

import com.kakaobean.core.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MultipleChoiceAnswerResponse extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long answerId;

    private String content;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MultipleChoiceQuestionResponse multipleChoiceQuestionResponse;

    public MultipleChoiceAnswerResponse(Long answerId, String content) {
        this.answerId = answerId;
        this.content = content;
    }

    public void addMultipleChoiceQuestion(MultipleChoiceQuestionResponse multipleChoiceQuestionResponse){
        this.multipleChoiceQuestionResponse = multipleChoiceQuestionResponse;
    }
}
