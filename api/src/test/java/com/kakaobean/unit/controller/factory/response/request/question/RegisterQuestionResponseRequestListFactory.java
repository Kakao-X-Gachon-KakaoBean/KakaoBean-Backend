package com.kakaobean.unit.controller.factory.response.request.question;


import com.kakaobean.response.dto.request.questionresponse.RegisterQuestionResponseRequest;

import java.util.List;

import static com.kakaobean.unit.controller.factory.response.request.question.RegisterQuestionResponseRequestFactory.*;

public class RegisterQuestionResponseRequestListFactory {

    private RegisterQuestionResponseRequestListFactory(){}

    public static List<RegisterQuestionResponseRequest> createSuccessQuestionResponseRequestList(){

        return List.of(
                createSuccessEssayQuestionResponseRequest(1L),
                createSuccessMultipleChoiceQuestionResponseRequestWithLogic(2L),
                createSuccessRangeQuestionResponseRequest(3L),
                createSuccessMultipleChoiceQuestionResponseRequestWithoutLogic(4L, "단일 선택 객관식 1"),
                createSuccessEssayQuestionResponseRequest(5L),
                createSuccessEssayQuestionResponseRequest(6L),
                createSuccessMultipleChoiceQuestionResponseRequestWithoutLogic(7L, "단일 선택 객관식 2")
        );

    }
}
