package com.kakaobean.core.factory.survey.question;

import com.kakaobean.core.survey.application.dto.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.question.RegisterQuestionRequestDto;
import org.springframework.scheduling.config.FixedRateTask;

import java.util.List;

import static com.kakaobean.core.factory.survey.question.RegisterQuestionRequestDtoFactory.*;

public class RegisterQuestionRequestListDtoFactory {

    private RegisterQuestionRequestListDtoFactory(){}

    private static final String FIRST_ANSWER = "first choice answer";
    private static final String SECOND_ANSWER = "second choice answer";
    private static final String THIRD_ANSWER = "third choice answer";
    private static final String FOURTH_ANSWER = "fourth choice answer";
    private static final String FIFTH_ANSWER = "fifth choice answer";

    /**
     * Rqnge Bar Question 성공 로직
     */
    public static List<RegisterQuestionRequestDto> createSuccessListRequest(){
        return List.of(
                createEssayQuestionSuccessRequest("1", false),
                createMultipleQuestionSuccessRequestWithLogic(
                        "2",
                        "3",
                        "4",
                        2,
                        false,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createRangeQuestionSuccessRequest("3", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "4",
                        1,
                        false,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createEssayQuestionSuccessRequest("5", false),
                createEssayQuestionSuccessRequest("6", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "7",
                        1,
                        true,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                )
        );
    }

    public static List<RegisterQuestionRequestDto> createFailListCase1Request(){
        return List.of(
                createEssayQuestionSuccessRequest("1", false),
                createMultipleQuestionSuccessRequestWithLogic(
                        "2",
                        "3",
                        "9", //존재하지 않는 다음 질문 값을 넣는다.
                        2,
                        false,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createRangeQuestionSuccessRequest("3", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "4",
                        1,
                        false,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createEssayQuestionSuccessRequest("5", false),
                createEssayQuestionSuccessRequest("6", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "7",
                        1,
                        false,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                )
        );
    }


    public static List<RegisterQuestionRequestDto> createFailListCase2Request(){
        return List.of(
                createEssayQuestionSuccessRequest("1", false),
                createMultipleQuestionFailRequestWithFailLogic(
                        "2",
                        "3",
                        "4",
                        2,
                        false,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createRangeQuestionSuccessRequest("3", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "4",
                        2,
                        false,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER                ),
                createEssayQuestionSuccessRequest("5", false),
                createEssayQuestionSuccessRequest("6", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "7",
                        1,
                        false,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                )
        );
    }
}