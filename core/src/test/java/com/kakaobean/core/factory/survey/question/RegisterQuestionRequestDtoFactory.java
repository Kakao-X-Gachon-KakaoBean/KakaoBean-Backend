package com.kakaobean.core.factory.survey.question;

import com.kakaobean.core.survey.application.dto.question.RegisterEssayQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.question.RegisterMultipleChoiceQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.question.RegisterRangeQuestionRequestDto;


import java.util.List;

public class RegisterQuestionRequestDtoFactory {

    private RegisterQuestionRequestDtoFactory(){}

    /**
     * Rqnge Bar Question 성공 로직
     */
    public static RegisterRangeQuestionRequestDto createRangeQuestionSuccessRequest(String questionNumber){
        return RegisterRangeQuestionRequestDto
                .builder()
                .title("Range Bar Question")
                .explanation("ex1")
                .questionNumber(questionNumber)
                .min(1)
                .max(10)
                .build();
    }

    /**
     * 주관식 성공 로직
     */
    public static RegisterEssayQuestionRequestDto createEssayQuestionSuccessRequest(String questionNumber){
        return RegisterEssayQuestionRequestDto
                .builder()
                .title("Essay Question Title")
                .explanation("ex2")
                .questionNumber(questionNumber)
                .build();
    }

    public static RegisterMultipleChoiceQuestionRequestDto createMultipleQuestionSuccessRequestWithLogic(
            String questionNumber,
            String firstNextQuestionNumber,
            String secondNextQuestionNumber,
            Integer numberOfAnswerChoices,
            String... answers
    ){
        return RegisterMultipleChoiceQuestionRequestDto
                .builder()
                .title("First Multiple Question Title")
                .explanation("ex3")
                .questionNumber(questionNumber)
                .numberOfAnswerChoices(numberOfAnswerChoices)
                .answers(List.of(answers))
                .conditions(
                      List.of(
                              RegisterQuestionFlowLogicRequestDtoFactory.createDto(firstNextQuestionNumber, answers[0], answers[1]),
                              RegisterQuestionFlowLogicRequestDtoFactory.createDto(secondNextQuestionNumber, answers[2], answers[3])
                      )
                )
                .build();
    }

    public static RegisterMultipleChoiceQuestionRequestDto createMultipleQuestionSuccessRequestWithoutLogic(
            String questionNumber,
            Integer numberOfAnswerChoices,
            String... answers
    ){
        return RegisterMultipleChoiceQuestionRequestDto
                .builder()
                .title("Multiple Question Title")
                .explanation("without logic")
                .questionNumber(questionNumber)
                .numberOfAnswerChoices(numberOfAnswerChoices)
                .answers(List.of(answers))
                .conditions(
                        List.of()
                )
                .build();
    }


    public static RegisterMultipleChoiceQuestionRequestDto createMultipleQuestionFailRequestWithFailLogic(
            String questionNumber,
            String firstNextQuestionNumber,
            String secondNextQuestionNumber,
            Integer numberOfAnswerChoices,
            String... answers
    ){
        return RegisterMultipleChoiceQuestionRequestDto
                .builder()
                .title("First Multiple Question Title")
                .explanation("ex3")
                .questionNumber(questionNumber)
                .numberOfAnswerChoices(numberOfAnswerChoices)
                .answers(List.of(answers))
                .conditions(
                        List.of(
                                //이상한 답 값을 넣는다.
                                RegisterQuestionFlowLogicRequestDtoFactory.createDto(firstNextQuestionNumber, "없는 답 예시", answers[1]),
                                RegisterQuestionFlowLogicRequestDtoFactory.createDto(secondNextQuestionNumber, answers[2], answers[3])
                        )
                )
                .build();
    }
}