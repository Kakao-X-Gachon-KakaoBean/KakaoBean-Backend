package com.kakaobean.core.factory.survey.question;

import com.kakaobean.core.survey.application.dto.request.question.RegisterEssayQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.request.question.RegisterMultipleChoiceQuestionRequestDto;
import com.kakaobean.core.survey.application.dto.request.question.RegisterRangeQuestionRequestDto;


import java.util.List;

public class RegisterQuestionRequestDtoFactory {

    private RegisterQuestionRequestDtoFactory(){}

    /**
     * Rqnge Bar Question 성공 로직
     */
    public static RegisterRangeQuestionRequestDto createRangeQuestionSuccessRequest(String questionNumber,
                                                                                    String nextQuestionNumber,
                                                                                    boolean finalQuestion){
        return RegisterRangeQuestionRequestDto
                .builder()
                .title("Range Bar Question")
                .explanation("ex1")
                .questionNumber(questionNumber)
                .min(1)
                .max(10)
                .finalQuestion(finalQuestion)
                .nextQuestionNumber(nextQuestionNumber)
                .build();
    }

    /**
     * 주관식 성공 로직
     */
    public static RegisterEssayQuestionRequestDto createEssayQuestionSuccessRequest(String questionNumber,
                                                                                    String nextQuestionNumber,
                                                                                    boolean finalQuestion){
        return RegisterEssayQuestionRequestDto
                .builder()
                .title("Essay Question Title")
                .explanation("ex2")
                .questionNumber(questionNumber)
                .finalQuestion(finalQuestion)
                .nextQuestionNumber(nextQuestionNumber)
                .build();
    }

    public static RegisterMultipleChoiceQuestionRequestDto createMultipleQuestionSuccessRequestWithLogic(String questionNumber,
                                                                                                         String logicFirstNextQuestionNumber,
                                                                                                         String logicSecondNextQuestionNumber,
                                                                                                         Integer numberOfAnswerChoices,
                                                                                                         String nextQuestionNumber,
                                                                                                         boolean finalQuestion,
                                                                                                         String... answers){
        return RegisterMultipleChoiceQuestionRequestDto
                .builder()
                .title("First Multiple Question Title")
                .explanation("ex3")
                .questionNumber(questionNumber)
                .numberOfAnswerChoices(numberOfAnswerChoices)
                .answers(List.of(answers))
                .conditions(
                      List.of(
                              RegisterQuestionFlowLogicRequestDtoFactory.createDto(logicFirstNextQuestionNumber, answers[0], answers[1]),
                              RegisterQuestionFlowLogicRequestDtoFactory.createDto(logicSecondNextQuestionNumber, answers[2], answers[3])
                      )
                )
                .finalQuestion(finalQuestion)
                .nextQuestionNumber(nextQuestionNumber)
                .build();
    }

    public static RegisterMultipleChoiceQuestionRequestDto createMultipleQuestionSuccessRequestWithoutLogic(String questionNumber,
                                                                                                            Integer numberOfAnswerChoices,
                                                                                                            boolean finalQuestion,
                                                                                                            String nextQuestionNumber,
                                                                                                            String... answers){
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
                .nextQuestionNumber(nextQuestionNumber)
                .finalQuestion(finalQuestion)
                .build();
    }


    public static RegisterMultipleChoiceQuestionRequestDto createMultipleQuestionFailRequestWithFailLogic(String questionNumber,
                                                                                                          String firstNextQuestionNumber,
                                                                                                          String secondNextQuestionNumber,
                                                                                                          Integer numberOfAnswerChoices,
                                                                                                          boolean finalQuestion,
                                                                                                          String nextQuestionNumber,
                                                                                                          String... answers){
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
                .finalQuestion(finalQuestion)
                .nextQuestionNumber(nextQuestionNumber)
                .build();
    }
}