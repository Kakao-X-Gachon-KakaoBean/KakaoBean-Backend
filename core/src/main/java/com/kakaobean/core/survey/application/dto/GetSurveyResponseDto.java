package com.kakaobean.core.survey.application.dto;

import com.kakaobean.core.survey.application.dto.question.*;
import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.essay.EssayQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionAnswer;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestionFlowLogic;
import com.kakaobean.core.survey.domain.question.range.RangeQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GetSurveyResponseDto {

    private List<GetQuestionResponseDto> questions;

    public GetSurveyResponseDto(List<Question> questions) {
        this.questions = questions.stream()
                .map(question -> getQusetionDto(question))
                .collect(Collectors.toList());

    }

    /**
     * DB에서 뽑아온 Question 객체들을 switch문으로 판단하여 각각의 Dto로 만드는 함수
     * @param question : Essay, Multiple, Range 등 설문에 대한 개별 문항이 들어간다.
     * @return : 생성된 GetQusetionResponseDto 구현체
     */
    private GetQuestionResponseDto getQusetionDto(Question question) {
        switch (question.getClass().getSimpleName()) {
            case "EssayQuestion":
                EssayQuestion essayQuestion = (EssayQuestion) question;
                return new GetEssayQuestionResponseDto(
                        essayQuestion.getTitle(),
                        essayQuestion.getExplanation(),
                        essayQuestion.getQuestionNumber(),
                        essayQuestion.isFinalQuestion(),
                        hasNextQuestion(essayQuestion.getNextQuestion()));

            case "RangeQuestion":
                RangeQuestion rangeQuestion = (RangeQuestion) question;
                return new GetRangeQuestionResponseDto(
                        rangeQuestion.getTitle(),
                        rangeQuestion.getExplanation(),
                        rangeQuestion.getQuestionNumber(),
                        rangeQuestion.isFinalQuestion(),
                        hasNextQuestion(rangeQuestion.getNextQuestion()),
                        rangeQuestion.getMin(),
                        rangeQuestion.getMax());

            case "MultipleChoiceQuestion":
                MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) question;
                return new GetMultipleChoiceQuestionResonseDto(
                        multipleChoiceQuestion.getTitle(),
                        multipleChoiceQuestion.getExplanation(),
                        multipleChoiceQuestion.getQuestionNumber(),
                        multipleChoiceQuestion.isFinalQuestion(),
                        hasNextQuestion(multipleChoiceQuestion.getNextQuestion()),
                        multipleChoiceQuestion.getNumberOfAnswerChoices(),
                        multipleChoiceQuestion.getAnswers().stream()
                                .map(MultipleChoiceQuestionAnswer::getContent).collect(Collectors.toList()),
                        multipleChoiceQuestion.getLogics().stream()
                                .map(this::getLogicDto)
                                .collect(Collectors.toList())
                );
            default:
                throw new IllegalArgumentException("Unknown question type: " + question.getClass());
        }
    }

    /**
     * 해당 질문이 마지막 값인지 확인한다.
     * @param question : 확인할 대상 질문
     * @return : 다음 질문이 없다면 0을 리턴한다.
     */
    private String hasNextQuestion(Question question){
        if (question!=null){
            return question.getQuestionNumber();
        }else {
            return "0";
        }
    }

    /**
     * 객관식에 존재하는 로직에 대한 dto를 만든다.
     * GetQuestionFlowLogicResponseDto에는
     * 1. 로직이 갖는 객관식 질문들과
     * 2. 로직이 터지면 향하는 다음 질문 번호를 넣어줘야한다.
     */
    private GetQuestionFlowLogicResponseDto getLogicDto(MultipleChoiceQuestionFlowLogic logic){

        return new GetQuestionFlowLogicResponseDto(
                logic.getConditions().stream()
                        .map(condition -> condition.getAnswer().getContent())
                        .collect(Collectors.toList()),
                logic.getNextQuestion().getQuestionNumber()
        );
    }

}
