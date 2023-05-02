package com.kakaobean.unit.controller.factory.survey.response.question;

import com.kakaobean.core.survey.application.dto.response.question.*;

import java.util.List;

public class FindQuestionResponseFactory {

    private FindQuestionResponseFactory(){}

    private static final String FIRST_ANSWER = "first choice answer";
    private static final String SECOND_ANSWER = "second choice answer";
    private static final String THIRD_ANSWER = "third choice answer";
    private static final String FOURTH_ANSWER = "fourth choice answer";
    private static final String FIFTH_ANSWER = "fifth choice answer";

    /**
     * Rqnge Bar Question 성공 로직
     */
    public static FindQuestionResponseDto createRangeQuestionSuccessResponse(String questionNumber,
                                                                            String nextQuestionNumber,
                                                                            Boolean isFinalQuestion){
        return FindRangeQuestionResponseDto
                .builder()
                .title("Range Bar Question")
                .explanation("ex1")
                .questionNumber(questionNumber)
                //.type(QuestionRequestType.RANGE)
                .min(1)
                .max(10)
                .nextQuestionNumber(nextQuestionNumber)
                .finalQuestion(isFinalQuestion)
                .questionId(1L)
                .build();
    }

    /**
     * 주관식 성공 로직
     */
    public static FindEssayQuestionResponseDto createEssayQuestionSuccessResponse(String questionNumber,
                                                                                 String nextQuestionNumber,
                                                                                 Boolean isFinalQuestion){
        return FindEssayQuestionResponseDto
                .builder()
                .title("Essay Question Title")
                .explanation("ex2")
                .questionNumber(questionNumber)
                //.type(QuestionRequestType.ESSAY)
                .nextQuestionNumber(nextQuestionNumber)
                .finalQuestion(isFinalQuestion)
                .questionId(2L)
                .build();
    }

    public static FindMultipleChoiceQuestionResponseDto createMultipleQuestionSuccessResponseWithLogic(String questionNumber,
                                                                                                       String nextQuestionNumber,
                                                                                                       Boolean isFinalQuestion){

        FindMultipleChoiceQuestionAnswerDto answer1 = new FindMultipleChoiceQuestionAnswerDto(3L, FIRST_ANSWER);
        FindMultipleChoiceQuestionAnswerDto answer2 = new FindMultipleChoiceQuestionAnswerDto(4L, SECOND_ANSWER);
        FindMultipleChoiceQuestionAnswerDto answer3 = new FindMultipleChoiceQuestionAnswerDto(5L, THIRD_ANSWER);
        FindMultipleChoiceQuestionAnswerDto answer4 = new FindMultipleChoiceQuestionAnswerDto(6L, FOURTH_ANSWER);
        FindMultipleChoiceQuestionAnswerDto answer5 = new FindMultipleChoiceQuestionAnswerDto(7L, FIFTH_ANSWER);

        return FindMultipleChoiceQuestionResponseDto
                .builder()
                .title("First Multiple Question Title")
                .explanation("ex3")
                .questionNumber(questionNumber)
                .numberOfAnswerChoices(2)
                .answers(List.of(answer1, answer2, answer3, answer4, answer5))
                .logics(
                      List.of(
                              new FindQuestionFlowLogicResponseDto(List.of(answer1, answer2), "3"),
                              new FindQuestionFlowLogicResponseDto(List.of(answer3, answer4), "4")
                      )
                )
                //.type(QuestionRequestType.MULTIPLE)
                .nextQuestionNumber(nextQuestionNumber)
                .finalQuestion(isFinalQuestion)
                .questionId(8L)
                .build();
    }

    public static FindMultipleChoiceQuestionResponseDto createMultipleQuestionSuccessResponseWithoutLogic(String questionNumber,
                                                                                                         Integer numberOfAnswerChoices,
                                                                                                         String nextQuestionNumber,
                                                                                                         Boolean isFinalQuestion){
        return FindMultipleChoiceQuestionResponseDto
                .builder()
                .title("Multiple Question Title")
                .explanation("without logic")
                .questionNumber(questionNumber)
                .numberOfAnswerChoices(numberOfAnswerChoices)
                .answers(List.of(
                        new FindMultipleChoiceQuestionAnswerDto(9L, FIRST_ANSWER),
                        new FindMultipleChoiceQuestionAnswerDto(10L, SECOND_ANSWER),
                        new FindMultipleChoiceQuestionAnswerDto(11L, FIRST_ANSWER),
                        new FindMultipleChoiceQuestionAnswerDto(12L, FIRST_ANSWER),
                        new FindMultipleChoiceQuestionAnswerDto(13L, FIRST_ANSWER)
                ))
                .nextQuestionNumber(nextQuestionNumber)
                .logics(
                        List.of()
                )
                //.type(QuestionRequestType.MULTIPLE)
                .finalQuestion(isFinalQuestion)
                .questionId(14L)
                .build();
    }
}