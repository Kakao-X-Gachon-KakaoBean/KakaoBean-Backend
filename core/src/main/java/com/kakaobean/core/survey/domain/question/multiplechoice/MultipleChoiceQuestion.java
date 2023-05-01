package com.kakaobean.core.survey.domain.question.multiplechoice;

import com.kakaobean.core.survey.application.dto.request.question.GetMultipleChoiceQuestionAnswerDto;
import com.kakaobean.core.survey.application.dto.request.question.GetMultipleChoiceQuestionResonseDto;
import com.kakaobean.core.survey.application.dto.request.question.GetQuestionFlowLogicResponseDto;
import com.kakaobean.core.survey.application.dto.request.question.GetQuestionResponseDto;
import com.kakaobean.core.survey.domain.question.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 객관식 질문
 */

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("multiple_choice_question")
public class MultipleChoiceQuestion extends Question {

    /**
     * 객관식 답변(ex. 1번, 2번, 3번, 4번, 5번)
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<MultipleChoiceQuestionAnswer> answers = new ArrayList<>();

    /**
     * 다음 답변으로 넘어갈 조건을 담은 로직
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<MultipleChoiceQuestionFlowLogic> logics = new ArrayList<>();

    /**
     * 답을 몇개까지 고를지 제한선을 설정.그리고 이게 로직에서도 제한되어야 함.
     */
    private Integer numberOfAnswerChoices;

    public MultipleChoiceQuestion(String title,
                                  String explanation,
                                  String questionNumber,
                                  List<MultipleChoiceQuestionAnswer> answers,
                                  Integer numberOfAnswerChoices,
                                  boolean finalQuestion) {
        super(title, explanation, questionNumber, finalQuestion);
        this.answers = answers;
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        answers.forEach(answer -> answer.addQuestion(this));
    }

    public void addLogics(List<MultipleChoiceQuestionFlowLogic> questionFlowLogics){
        this.logics.addAll(questionFlowLogics);
    }


    @Override
    protected void detailValidate() {
        validateLogicWithSameConditions();
    }

    //겹치는 로직이 있으면 안됨. (동일한 답변인데 다른 질문을 향하거나 동일한 로직이 2개)
    private void validateLogicWithSameConditions() {
        for (int i = 0; i < logics.size() - 1; i++) {
            MultipleChoiceQuestionFlowLogic standard = logics.get(i);
            for (int j = i + 1; j < logics.size(); j++) {
                standard.compareWithOtherLogic(logics.get(j));
            }
        }
    }

    @Override
    protected GetQuestionResponseDto createDetailServiceDto() {
        return new GetMultipleChoiceQuestionResonseDto(
                getId(),
                getTitle(),
                getExplanation(),
                getQuestionNumber(),
                isFinalQuestion(),
                hasNextQuestion(getNextQuestion()),
                getNumberOfAnswerChoices(),
                getAnswers().stream()
                        .map(answer-> getAnswerDto(answer))
                        .collect(Collectors.toList()),
                getLogics().stream().map(logic -> getLogicDto(logic)).collect(Collectors.toList())
        );
    }

    /**
     * 객관식에 들어가는 질문에 대한 dto를 만든다.
     * dto 안에는 질문id와 질문content가 들어간다.
     */
    protected GetMultipleChoiceQuestionAnswerDto getAnswerDto(MultipleChoiceQuestionAnswer answer){
        return new GetMultipleChoiceQuestionAnswerDto(
                answer.getId(),
                answer.getContent()
        );
    }

    /**
     * 객관식에 존재하는 로직에 대한 dto를 만든다.
     */
    protected GetQuestionFlowLogicResponseDto getLogicDto(MultipleChoiceQuestionFlowLogic logic){
        return new GetQuestionFlowLogicResponseDto(
                logic.getConditions().stream()
                        .map(condition -> getAnswerDto(condition.getAnswer()))
                        .collect(Collectors.toList()),
                logic.getNextQuestion().getQuestionNumber()
        );
    }
}
