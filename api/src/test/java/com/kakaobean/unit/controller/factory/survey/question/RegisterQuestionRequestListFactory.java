package com.kakaobean.unit.controller.factory.survey.question;

import com.kakaobean.core.survey.application.dto.QuestionRequestType;
import com.kakaobean.survey.dto.request.question.RegisterQuestionRequest;
import com.kakaobean.survey.dto.request.question.RegisterRangeQuestionRequest;

import java.util.List;

import static com.kakaobean.unit.controller.factory.survey.question.RegisterQuestionRequestFactory.*;

public class RegisterQuestionRequestListFactory {

    private RegisterQuestionRequestListFactory(){}

    /**
     * Rqnge Bar Question 성공 로직
     */
    public static List<RegisterQuestionRequest> createSuccessListRequest(){
        return List.of(
                createEssayQuestionSuccessRequest("1"),
                createMultipleQuestionSuccessRequestWithLogic("2"),
                createRangeQuestionSuccessRequest("3"),
                createMultipleQuestionSuccessRequestWithoutLogic("4", 1),
                createEssayQuestionSuccessRequest("5"),
                createEssayQuestionSuccessRequest("6"),
                createMultipleQuestionSuccessRequestWithoutLogic("7", 1)
        );
    }
}