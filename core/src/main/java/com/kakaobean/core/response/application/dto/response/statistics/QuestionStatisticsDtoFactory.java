package com.kakaobean.core.response.application.dto.response.statistics;

import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.response.domain.questionresponse.MultipleChoiceQuestionResponse;
import com.kakaobean.core.survey.domain.question.Question;
import com.kakaobean.core.survey.domain.question.essay.EssayQuestion;
import com.kakaobean.core.survey.domain.question.multiplechoice.MultipleChoiceQuestion;
import com.kakaobean.core.survey.domain.question.range.RangeQuestion;

import java.util.List;
import java.util.stream.Collectors;

import static com.kakaobean.core.survey.application.dto.QuestionDTOType.*;

public class QuestionStatisticsDtoFactory {

    private QuestionStatisticsDtoFactory(){}

    public static QuestionStatisticsDto createDto(Question question,
                                                  Integer numberOfResponse,
                                                  List<SurveyResponseDto> allResponses){

        Class<? extends Question> questionClass = question.getClass();

        if(questionClass == MultipleChoiceQuestion.class){
            MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) question;
            return new MultipleQuestionStatisticsDto(
                    MULTIPLE,
                    multipleChoiceQuestion.getTitle(),
                    multipleChoiceQuestion.getExplanation(),
                    multipleChoiceQuestion,
                    allResponses
                    // makeAnswerResponseDto(response));
            );
        }

        else if(questionClass == RangeQuestion.class){
            RangeQuestion rangeQuestion = (RangeQuestion) question;
            return new RangeQuestionStatisticsDto(
                    RANGE,
                    rangeQuestion.getTitle(),
                    rangeQuestion.getExplanation(),
                    rangeQuestion.getMin(),
                    rangeQuestion.getMax(),
                    rangeQuestion,
                    allResponses
            );
        }

        else if(questionClass == EssayQuestion.class){
            EssayQuestion essayQuestion = (EssayQuestion) question;
            return new EssayQuestionStatisticsDto(
                    ESSAY,
                    essayQuestion.getTitle(),
                    essayQuestion.getExplanation(),
                    essayQuestion,
                    allResponses
            );
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
