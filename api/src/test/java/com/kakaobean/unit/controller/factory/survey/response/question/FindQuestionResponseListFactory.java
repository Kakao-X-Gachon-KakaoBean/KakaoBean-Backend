package com.kakaobean.unit.controller.factory.survey.response.question;

import com.kakaobean.core.survey.application.dto.response.question.FindQuestionResponseDto;

import java.util.List;

import static com.kakaobean.unit.controller.factory.survey.response.question.FindQuestionResponseFactory.*;

public class FindQuestionResponseListFactory {

    private FindQuestionResponseListFactory(){}

    /**
     * Rqnge Bar Question 성공 로직
     */
    public static List<FindQuestionResponseDto> createSuccessListRequest(){
        return List.of(
                createEssayQuestionSuccessResponse("1", "2", false),
                createMultipleQuestionSuccessResponseWithLogic("2", "7   ",false),
                createRangeQuestionSuccessResponse("3", "5", false),
                createMultipleQuestionSuccessResponseWithoutLogic("4", 1, "5", false),
                createEssayQuestionSuccessResponse("5" , "7", false),
                createEssayQuestionSuccessResponse("6", "7", false),
                createMultipleQuestionSuccessResponseWithoutLogic("7", 1, "0", true)
        );
    }
}