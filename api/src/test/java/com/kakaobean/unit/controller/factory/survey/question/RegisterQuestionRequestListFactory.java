package com.kakaobean.unit.controller.factory.survey.question;

import com.kakaobean.survey.dto.request.question.RegisterQuestionRequest;

import java.util.List;

import static com.kakaobean.unit.controller.factory.survey.question.RegisterQuestionRequestFactory.*;

public class RegisterQuestionRequestListFactory {

    private RegisterQuestionRequestListFactory(){}

    /**
     * Rqnge Bar Question 성공 로직
     */
    public static List<RegisterQuestionRequest> createSuccessListRequest(){
        return List.of(
                createEssayQuestionSuccessRequest("1", "2", false),
                createMultipleQuestionSuccessRequestWithLogic("2", "0",false),
                createRangeQuestionSuccessRequest("3", "5", false),
                createMultipleQuestionSuccessRequestWithoutLogic("4", 1, "5", false),
                createEssayQuestionSuccessRequest("5" , "7", false),
                createEssayQuestionSuccessRequest("6", "7", false),
                createMultipleQuestionSuccessRequestWithoutLogic("7", 1, "0", true)
        );
    }
}