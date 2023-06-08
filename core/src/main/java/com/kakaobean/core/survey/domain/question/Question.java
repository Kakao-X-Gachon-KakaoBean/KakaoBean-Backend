package com.kakaobean.core.survey.domain.question;

import com.kakaobean.core.common.domain.BaseEntity;
import com.kakaobean.core.common.domain.BaseStatus;
import com.kakaobean.core.survey.application.dto.response.question.FindQuestionResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //조인 전략과 여기만 바뀜
@DiscriminatorColumn(name = "question_type")
public abstract class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Survey survey;

    private String title;

    private String explanation;

    private String questionNumber;

    private boolean finalQuestion;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Question nextQuestion;

    public Question(String title, String explanation, String questionNumber, boolean finalQuestion) {
        super(BaseStatus.ACTIVE);
        this.title = title;
        this.explanation = explanation;
        this.questionNumber = questionNumber;
        this.finalQuestion = finalQuestion;
    }

    public void addSurvey(Survey survey) {
        this.survey = survey;
    }

    public void addNextQuestion(Question nextQuestion) {
        this.nextQuestion = nextQuestion;
    }

    public void validate(){
        detailValidate();
    }

    protected abstract void detailValidate();

    public FindQuestionResponseDto toServiceDto(){
        return createDetailServiceDto();
    }

    protected abstract FindQuestionResponseDto createDetailServiceDto();

    /**
     * 해당 질문이 마지막 값인지 확인한다.
     */
    protected String hasNextQuestion(Question question){
        if (question!=null){
            return question.getQuestionNumber();
        }else {
            return "0";
        }
    }

}

