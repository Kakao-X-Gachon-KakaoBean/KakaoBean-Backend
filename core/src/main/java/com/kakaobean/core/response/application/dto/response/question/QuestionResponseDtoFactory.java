package com.kakaobean.core.response.application.dto.response.question;

import com.kakaobean.core.response.domain.questionresponse.EssayQuestionResponse;
import com.kakaobean.core.response.domain.questionresponse.MultipleChoiceQuestionResponse;
import com.kakaobean.core.response.domain.questionresponse.QuestionResponse;
import com.kakaobean.core.response.domain.questionresponse.RangeQuestionResponse;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;

import java.util.List;
import java.util.stream.Collectors;

import static com.kakaobean.core.survey.application.dto.QuestionDTOType.*;

public class QuestionResponseDtoFactory {

    private QuestionResponseDtoFactory(){}

    public static QuestionResponseDto createDto(QuestionResponse questionResponse){
        Class<? extends QuestionResponse> questionResponseClass = questionResponse.getClass();

        if(questionResponseClass == MultipleChoiceQuestionResponse.class){
            MultipleChoiceQuestionResponse response = (MultipleChoiceQuestionResponse) questionResponse;
            return new MultipleChoiceQuestionResponseDto(response.getQuestionId(), MULTIPLE, makeAnswerResponseDto(response));
        }

        else if(questionResponseClass == RangeQuestionResponse.class){
            RangeQuestionResponse response = (RangeQuestionResponse) questionResponse;
            return new RangeQuestionResponseDto(response.getQuestionId(), RANGE, response.getAnswerValue());
        }

        else if(questionResponseClass == EssayQuestionResponse.class){
            EssayQuestionResponse response = (EssayQuestionResponse) questionResponse;
            return new EssayQuestionResponseDto(response.getQuestionId(), ESSAY, response.getAnswer());
        }

        throw new RuntimeException("없는 클래스 인데..?");
    }

    private static List<String> makeAnswerResponseDto(MultipleChoiceQuestionResponse response) {
        return response.getAnswerResponses()
                .stream()
                .map(answerResponse -> answerResponse.getContent())
                .collect(Collectors.toList());
    }
}
