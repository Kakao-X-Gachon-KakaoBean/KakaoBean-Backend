package com.kakaobean.core.survey.domain.question.multiplechoice;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.EmitUtils;

import javax.persistence.*;

/**
 * 객관식 답변(ex. 1번, 2번, 3번, 4번, 5번)
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "multiple_choice_question_answer")
public class MultipleChoiceQuestionAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    /**
     * 객관식 답변들이 포함된 객관식 질문.
     */
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MultipleChoiceQuestion question;

    public MultipleChoiceQuestionAnswer(String content) {
        super(BaseStatus.ACTIVE);
        this.content = content;
    }

    public void addQuestion(MultipleChoiceQuestion multipleChoiceQuestion) {
        this.question = multipleChoiceQuestion;
    }
}
