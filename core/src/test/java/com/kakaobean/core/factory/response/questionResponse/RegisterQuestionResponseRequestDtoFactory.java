package com.kakaobean.core.factory.response.questionResponse;


import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterEssayQuestionResponseRequestDto;
import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterMultipleChoiceQuestionResponseRequestDto;
import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterRangeQuestionResponseReqeusetDto;

import java.util.List;

public class RegisterQuestionResponseRequestDtoFactory {

    private RegisterQuestionResponseRequestDtoFactory(){}

    /**
     * Essay
     */
    public static RegisterEssayQuestionResponseRequestDto createEssayQuestionResponseSuccessRequest(Long questionId){
        return RegisterEssayQuestionResponseRequestDto
                .builder()
                .questionId(questionId)
                .answer("에세이 질문 답변")
                .build();
    }

    /**
     * Range
     */
    public static RegisterRangeQuestionResponseReqeusetDto createRangeQuestionResponseSuccessRequest(Long questionId,
                                                                                                     Integer answerValue){
        return RegisterRangeQuestionResponseReqeusetDto
                .builder()
                .questionId(questionId)
                .answerValue(answerValue)
                .build();
    }

    public static RegisterMultipleChoiceQuestionResponseRequestDto createMultipleChoiceQuestionResponseSuccessRequest(Long questionId){
        return RegisterMultipleChoiceQuestionResponseRequestDto
                .builder()
                .questionId(questionId)
                .answers(
                        List.of(RegisterMultipleChoiceAnswerRequestDtoFactory.createDto(1L, "손흥민"),
                                RegisterMultipleChoiceAnswerRequestDtoFactory.createDto(2L, "조연겸")
                        )
                )
                .build();
    }
}
