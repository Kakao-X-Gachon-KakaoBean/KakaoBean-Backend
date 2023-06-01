package com.kakaobean.unit.controller.factory.response.response;

import com.kakaobean.core.member.domain.Gender;
import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.kakaobean.core.response.application.dto.response.question.EssayQuestionResponseDto;
import com.kakaobean.core.response.application.dto.response.question.MultipleChoiceQuestionResponseDto;
import com.kakaobean.core.response.application.dto.response.question.RangeQuestionResponseDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;

import java.util.List;

import static com.kakaobean.core.survey.application.dto.QuestionDTOType.*;

public class SurveyResponseDtoFactory {

    private SurveyResponseDtoFactory(){}

    public static List<SurveyResponseDto> createList(){
        return List.of(
                new SurveyResponseDto(
                        Gender.MALE,
                        25,
                        "123@gmail.com",
                        "김민수",
                        List.of(
                                new EssayQuestionResponseDto(1L, ESSAY,"Essay Question Title", "essay answer 2"),
                                new MultipleChoiceQuestionResponseDto(8L, MULTIPLE, "Multiple Question Title",List.of("third choice answer")),
                                new RangeQuestionResponseDto(12L, RANGE, "Range Question Title", 6),
                                new MultipleChoiceQuestionResponseDto(14L, MULTIPLE, "Multiple Question Title", List.of("fourth choice answer")),
                                new EssayQuestionResponseDto(15L, ESSAY, "Essay Question Title", "essay answer 8"),
                                new EssayQuestionResponseDto(16L, ESSAY, "Essay Question Title", "essay answer 9"),
                                new MultipleChoiceQuestionResponseDto(14L, MULTIPLE, "Multiple Question Title", List.of("fifth choice answer"))
                                )
                ),
                new SurveyResponseDto(
                        Gender.FEMALE,
                        24,
                        "1234@gmail.com",
                        "이민수",
                        List.of(
                                new EssayQuestionResponseDto(1L, ESSAY, "Essay Question Title", "essay answer 1"),
                                new MultipleChoiceQuestionResponseDto(8L, MULTIPLE,"Multiple Question Title", List.of("first choice answer", "second choice answer")),
                                new RangeQuestionResponseDto(12L, RANGE, "Range Question Title",  5),
                                new MultipleChoiceQuestionResponseDto(14L, MULTIPLE, "Multiple Question Title", List.of("third choice answer")),
                                new EssayQuestionResponseDto(15L, ESSAY, "Essay Question Title", "essay answer 4"),
                                new EssayQuestionResponseDto(16L, ESSAY, "Essay Question Title", "essay answer 5"),
                                new MultipleChoiceQuestionResponseDto(14L, MULTIPLE, "Multiple Question Title", List.of("fifth choice answer"))
                        )
                ),
                new SurveyResponseDto(
                        Gender.UNKNOWN,
                        25,
                        "12345@gmail.com",
                        "박민수",
                        List.of(
                                new EssayQuestionResponseDto(1L, ESSAY, "Essay Question Title", "essay answer 10"),
                                new MultipleChoiceQuestionResponseDto(8L, MULTIPLE, "Multiple Question Title", List.of("second choice answer", "third choice answer")),
                                new RangeQuestionResponseDto(12L, RANGE, "Range Question Title",  9),
                                new MultipleChoiceQuestionResponseDto(14L, MULTIPLE, "Multiple Question Title", List.of("fourth choice answer")),
                                new EssayQuestionResponseDto(15L, ESSAY, "Essay Question Title",  "essay answer 11"),
                                new EssayQuestionResponseDto(16L, ESSAY, "Essay Question Title", "essay answer 12"),
                                new MultipleChoiceQuestionResponseDto(14L, MULTIPLE, "Multiple Question Title", List.of("second choice answer"))
                        )
                )
        );
    }
}
