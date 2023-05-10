package com.kakaobean.unit.controller.factory.response.request.question;

import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import com.kakaobean.response.dto.request.questionresponse.RegisterEssayQuestionResponseRequest;
import com.kakaobean.response.dto.request.questionresponse.RegisterMultipleChoiceQuestionResponseRequest;
import com.kakaobean.response.dto.request.questionresponse.RegisterMutipleChoiceAnswerResponseRequest;
import com.kakaobean.response.dto.request.questionresponse.RegisterRangeQuestionResponseRequest;

import java.util.List;

public class RegisterQuestionResponseRequestFactory {

    private RegisterQuestionResponseRequestFactory(){}

    /**
     * Essay
     */
    public static RegisterEssayQuestionResponseRequest createSuccessEssayQuestionResponseRequest(Long questionId){
            return RegisterEssayQuestionResponseRequest
                    .builder()
                    .type(QuestionDTOType.ESSAY)
                    .queestionId(questionId)
                    .answers("에세이 질문 답변")
                    .build();
    }

    /**
     * Range
     */
    public static RegisterRangeQuestionResponseRequest createSuccessRangeQuestionResponseRequest(Long questionId){
            return RegisterRangeQuestionResponseRequest
                    .builder()
                    .type(QuestionDTOType.RANGE)
                    .questionId(questionId)
                    .answers(7)
                    .build();
    }

    /**
     * MultipleChoiceQuestion
     */
    public static RegisterMultipleChoiceQuestionResponseRequest createSuccessMultipleChoiceQuestionResponseRequestWithLogic(Long questionId){
            return RegisterMultipleChoiceQuestionResponseRequest
                    .builder()
                    .type(QuestionDTOType.MULTIPLE)
                    .questionId(questionId)
                    .answers(List.of(
                                RegisterMutipleChoiceAnswerResponseRequest
                                        .builder()
                                        .answerId(1L)
                                        .content("손흥민")
                                        .build(),
                                RegisterMutipleChoiceAnswerResponseRequest
                                        .builder()
                                        .answerId(2L)
                                        .content("조연겸")
                                        .build()
                                )
                        ).build();
    }

    /**
     * MultipleChoiceQuestion without Logic
     */
    public static RegisterMultipleChoiceQuestionResponseRequest createSuccessMultipleChoiceQuestionResponseRequestWithoutLogic(Long questionId, String content){
        return RegisterMultipleChoiceQuestionResponseRequest
                .builder()
                .type(QuestionDTOType.MULTIPLE)
                .questionId(questionId)
                .answers(List.of(
                                RegisterMutipleChoiceAnswerResponseRequest
                                        .builder()
                                        .answerId(1L)
                                        .content(content)
                                        .build()
                        )
                ).build();
    }

}
