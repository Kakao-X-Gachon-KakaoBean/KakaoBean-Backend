package com.kakaobean.unit.controller.factory.response.response.statistics;

import com.kakaobean.core.response.application.dto.response.statistics.*;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class QuestionStatisticsDtoFactory {

    public static List<QuestionStatisticsDto> createDtoList(){
        return List.of(
                new EssayQuestionStatisticsDto(
                        QuestionDTOType.ESSAY,
                        "Essay Question Title",
                        "explanation",
                        List.of("에세이 답변1", "에세이 답변2", "에세이 답변3")
                ),
                new RangeQuestionStatisticsDto(
                        QuestionDTOType.RANGE,
                        "Range Bar Question",
                        "explanation",
                        1,
                        10,
                        List.of(
                                new AnswerStatisticsDto("6",67),
                                new AnswerStatisticsDto("3",33)
                        )
                ),
                new MultipleQuestionStatisticsDto(
                        QuestionDTOType.MULTIPLE,
                        "Multiple Question Title",
                        "explanation",
                        List.of(
                                new AnswerStatisticsDto("보기 1번", 67),
                                new AnswerStatisticsDto("보기 2번", 33)
                        )
                )
        );
    }
}
