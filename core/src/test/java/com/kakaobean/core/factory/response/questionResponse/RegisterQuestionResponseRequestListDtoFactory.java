package com.kakaobean.core.factory.response.questionResponse;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterQuestionResponseRequestDto;
import com.kakaobean.core.survey.domain.Survey;

import java.util.List;
import java.util.stream.Collectors;

import static com.kakaobean.core.factory.response.questionResponse.RegisterQuestionResponseRequestDtoFactory.*;

public class RegisterQuestionResponseRequestListDtoFactory {

    private RegisterQuestionResponseRequestListDtoFactory(){}

    // 설문 응답 생성 성공 case1의 Dto 리스트
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

    // 설문 결과 통계를 위한 응답 생성 성공 case2의 Dto 리스트
    public static List<RegisterQuestionResponseRequestDto> createSuccessListRequestForStatistics(Survey survey){

        // 생성된 설문에 각 질문 아이디를 담는 List
        List<Long> collect = survey.getQuestions().stream()
                .map(question -> question.getId())
                .collect(Collectors.toList());

        // 질문 유형 순서대로 넣어야함. essay -> mul -> range -> mul -> essay -> essay -> mul
        return List.of(
                createEssayQuestionResponseSuccessRequest(collect.get(0)),
                createMultipleChoiceQuestionResponseSuccessRequest(collect.get(1)),
                createRangeQuestionResponseSuccessRequest(collect.get(2), 7),
                createMultipleChoiceQuestionResponseSuccessRequest(collect.get(3)),
                createEssayQuestionResponseSuccessRequest(collect.get(4)),
                createEssayQuestionResponseSuccessRequest(collect.get(5)),
                createMultipleChoiceQuestionResponseSuccessRequest(collect.get(6))
        );
    }

}
