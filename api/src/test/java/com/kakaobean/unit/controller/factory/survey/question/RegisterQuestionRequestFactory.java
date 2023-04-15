package com.kakaobean.unit.controller.factory.survey.question;

import com.kakaobean.core.survey.application.dto.QuestionRequestType;
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
    public static RegisterRangeQuestionRequest createRangeQuestionSuccessRequest(String questionNumber){
        return RegisterRangeQuestionRequest
                .builder()
                .title("Range Bar Question")
                .explanation("ex1")
                .questionNumber(questionNumber)
                .type(QuestionRequestType.RANGE)
                .min(1)
                .max(10)
                .build();
    }

    /**
     * 주관식 성공 로직
     */
    public static RegisterEssayQuestionRequest createEssayQuestionSuccessRequest(String questionNumber){
        return RegisterEssayQuestionRequest
                .builder()
                .title("Essay Question Title")
                .explanation("ex2")
                .questionNumber(questionNumber)
                .type(QuestionRequestType.RANGE)
                .build();
    }

    public static RegisterMultipleChoiceQuestionRequest createMultipleQuestionSuccessRequestWithLogic(
            String questionNumber
    ){
        return RegisterMultipleChoiceQuestionRequest
                .builder()
                .title("First Multiple Question Title")
                .explanation("ex3")
                .questionNumber(questionNumber)
                .numberOfAnswerChoices(2)
                .answers(List.of(
                        FIFTH_ANSWER,
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
                .type(QuestionRequestType.MULTIPLE)
                .build();
    }

    public static RegisterMultipleChoiceQuestionRequest createMultipleQuestionSuccessRequestWithoutLogic(
            String questionNumber,
            Integer numberOfAnswerChoices
    ){
        return RegisterMultipleChoiceQuestionRequest
                .builder()
                .title("Multiple Question Title")
                .explanation("without logic")
                .questionNumber(questionNumber)
                .numberOfAnswerChoices(1)
                .answers(List.of(
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ))
                .logics(
                        List.of()
                )
                .type(QuestionRequestType.MULTIPLE)
                .build();
    }
}