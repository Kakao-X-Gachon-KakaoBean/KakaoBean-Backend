package com.kakaobean.core.factory.response.questionResponse;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterQuestionResponseRequestDto;

import java.util.List;

import static com.kakaobean.core.factory.response.questionResponse.RegisterQuestionResponseRequestDtoFactory.*;

public class RegisterQuestionResponseRequestListDtoFactory {

    private RegisterQuestionResponseRequestListDtoFactory(){}

    public static List<RegisterQuestionResponseRequestDto> createSuccessListRequest(){
        return List.of(
                createEssayQuestionResponseSuccessRequest(1L),
                createMultipleChoiceQuestionResponseSuccessRequest(2L),
                createRangeQuestionResponseSuccessRequest(3L, 7),
                createMultipleChoiceQuestionResponseSuccessRequest(4L),
                createEssayQuestionResponseSuccessRequest(5L),
                createEssayQuestionResponseSuccessRequest(6L),
                createMultipleChoiceQuestionResponseSuccessRequest(7L)
        );
    }

}
