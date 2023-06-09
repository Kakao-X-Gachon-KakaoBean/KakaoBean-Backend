package com.kakaobean.unit.controller.factory.survey.request.question;

import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import com.kakaobean.survey.dto.request.question.RegisterEssayQuestionRequest;
import com.kakaobean.survey.dto.request.question.RegisterMultipleChoiceQuestionRequest;
import com.kakaobean.survey.dto.request.question.RegisterQuestionFlowLogicRequest;
import com.kakaobean.survey.dto.request.question.RegisterRangeQuestionRequest;

import java.util.List;

public class RegisterQuestionRequestFactory {

    private RegisterQuestionRequestFactory(){}

    private static final String FIRST_ANSWER = "first choice answer";
    private static final String SECOND_ANSWER = "second choice answer";
    private static final String THIRD_ANSWER = "third choice answer";
    private static final String FOURTH_ANSWER = "fourth choice answer";
    private static final String FIFTH_ANSWER = "fifth choice answer";

    /**
     * Rqnge Bar Question 성공 로직
     */
    public static RegisterRangeQuestionRequest createRangeQuestionSuccessRequest(String questionNumber,
                                                                                 String nextQuestionNumber,
                                                                                 Boolean isFinalQuestion){
        return RegisterRangeQuestionRequest
                .builder()
                .title("Range Bar Question")
                .explanation("ex1")
                .questionNumber(questionNumber)
                .type(QuestionDTOType.RANGE)
                .min(1)
                .max(10)
                .nextQuestionNumber(nextQuestionNumber)
                .finalQuestion(isFinalQuestion)
                .build();
    }

    /**
     * 주관식 성공 로직
     */
    public static RegisterEssayQuestionRequest createEssayQuestionSuccessRequest(String questionNumber,
                                                                                 String nextQuestionNumber,
                                                                                 Boolean isFinalQuestion){
        return RegisterEssayQuestionRequest
                .builder()
                .title("Essay Question Title")
                .explanation("ex2")
                .questionNumber(questionNumber)
                .type(QuestionDTOType.ESSAY)
                .nextQuestionNumber(nextQuestionNumber)
                .finalQuestion(isFinalQuestion)
                .build();
    }

    public static RegisterMultipleChoiceQuestionRequest createMultipleQuestionSuccessRequestWithLogic(String questionNumber,
                                                                                                      String nextQuestionNumber,
                                                                                                      Boolean isFinalQuestion){
        return RegisterMultipleChoiceQuestionRequest
                .builder()
                .title("First Multiple Question Title")
                .explanation("ex3")
                .questionNumber(questionNumber)
                .numberOfAnswerChoices(2)
                .answers(List.of(
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                        ))
                .logics(
                      List.of(
                              RegisterQuestionFlowLogicRequest.builder()
                                      .conditionOfQuestionAnswers(
                                              List.of(FIRST_ANSWER, SECOND_ANSWER)
                                      )
                                      .nextQuestionNumber("3")
                                      .build(),
                              RegisterQuestionFlowLogicRequest.builder()
                                      .conditionOfQuestionAnswers(
                                              List.of(THIRD_ANSWER, FOURTH_ANSWER)
                                      )
                                      .nextQuestionNumber("4")
                                      .build()
                      )
                )
                .type(QuestionDTOType.MULTIPLE)
                .nextQuestionNumber(nextQuestionNumber)
                .finalQuestion(isFinalQuestion)
                .build();
    }

    public static RegisterMultipleChoiceQuestionRequest createMultipleQuestionSuccessRequestWithoutLogic(String questionNumber,
                                                                                                         Integer numberOfAnswerChoices,
                                                                                                         String nextQuestionNumber,
                                                                                                         Boolean isFinalQuestion){
        return RegisterMultipleChoiceQuestionRequest
                .builder()
                .title("Multiple Question Title")
                .explanation("without logic")
                .questionNumber(questionNumber)
                .numberOfAnswerChoices(numberOfAnswerChoices)
                .answers(List.of(
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ))
                .nextQuestionNumber(nextQuestionNumber)
                .logics(
                        List.of()
                )
                .type(QuestionDTOType.MULTIPLE)
                .finalQuestion(isFinalQuestion)
                .build();
    }
}