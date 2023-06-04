package com.kakaobean.core.factory.survey.question;

import com.kakaobean.core.survey.application.dto.request.question.RegisterQuestionRequestDto;

import java.util.List;

import static com.kakaobean.core.factory.survey.question.RegisterQuestionRequestDtoFactory.*;

public class RegisterQuestionRequestListDtoFactory {

    private RegisterQuestionRequestListDtoFactory() {
    }

    private static final String FIRST_ANSWER = "first choice answer";
    private static final String SECOND_ANSWER = "second choice answer";
    private static final String THIRD_ANSWER = "third choice answer";
    private static final String FOURTH_ANSWER = "fourth choice answer";
    private static final String FIFTH_ANSWER = "fifth choice answer";

    /**
     * Rqnge Bar Question 성공 로직
     */
    public static List<RegisterQuestionRequestDto> createSuccessListRequest() {
        return List.of(
                createEssayQuestionSuccessRequest("1", "2", false),
                createMultipleQuestionSuccessRequestWithLogic(
                        "2",
                        "3",
                        "4",
                        2,
                        "0",
                        false,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createRangeQuestionSuccessRequest("3", "5", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "4",
                        1,
                        false,
                        "6",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createEssayQuestionSuccessRequest("5", "7", false),
                createEssayQuestionSuccessRequest("6", "7", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "7",
                        1,
                        true,
                        "0",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                )
        );
    }

    public static List<RegisterQuestionRequestDto> createFailListCase1Request() {
        return List.of(
                createEssayQuestionSuccessRequest("1", "0", true),
                createMultipleQuestionSuccessRequestWithLogic(
                        "2",
                        "3",
                        "4",
                        2,
                        "0",
                        false,
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createRangeQuestionSuccessRequest("3", "9", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "4",
                        1,
                        false,
                        "6",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createEssayQuestionSuccessRequest("5", "7", false),
                createEssayQuestionSuccessRequest("6", "7", false),
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


    public static List<RegisterQuestionRequestDto> createFailListCase2Request() {
        return List.of(
                createEssayQuestionSuccessRequest("1", "2", false),
                createMultipleQuestionFailRequestWithFailLogic(
                        "2",
                        "3",
                        "4",
                        2,
                        false,
                        "0",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createRangeQuestionSuccessRequest("3", "4", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "4",
                        2,
                        false,
                        "6",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER),
                createEssayQuestionSuccessRequest("5", "7", false),
                createEssayQuestionSuccessRequest("6", "7", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "7",
                        1,
                        true,
                        "0",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                )
        );
    }

    public static List<RegisterQuestionRequestDto> createFailListCase3Request() {
        return List.of(
                createEssayQuestionSuccessRequest("1", "2", false),
                createMultipleQuestionFailRequestWithFailLogic(
                        "2",
                        "9",
                        "4",
                        2,
                        false,
                        "0",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                ),
                createRangeQuestionSuccessRequest("3", "4", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "4",
                        2,
                        false,
                        "6",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER),
                createEssayQuestionSuccessRequest("5", "7", false),
                createEssayQuestionSuccessRequest("6", "7", false),
                createMultipleQuestionSuccessRequestWithoutLogic(
                        "7",
                        1,
                        true,
                        "0",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER
                )
        );
    }

    public static List<RegisterQuestionRequestDto> createFailListCase4Request() {
        return List.of(
                createEssayQuestionSuccessRequest("6", "0", false),
                createEssayQuestionSuccessRequest("6", "0", true)
        );
    }

    // 동일한 조건을 갖는 로직이 2개 이상인 케이스
    public static List<RegisterQuestionRequestDto> createFailListCase5Request() {
        return List.of(
                createRangeQuestionFailRequest("1", "0", true)
                );
    }

    // 동일한 조건을 갖는 로직이 2개 이상인 케이스
    public static List<RegisterQuestionRequestDto> createFailListCase6Request() {
        return List.of(
                createMultipleQuestionFailRequestWithSameLogic(
                        "1", "2", "3",
                        2, false, "0",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER),
                createEssayQuestionSuccessRequest("2", "3", false),
                createEssayQuestionSuccessRequest("3", "0", true)
        );
    }

    // 동일한 조건을 갖는 로직이 2개 이상인 케이스 + 이동하는 질문의 번호도 같음
    public static List<RegisterQuestionRequestDto> createFailListCase7Request() {
        return List.of(
                createMultipleQuestionFailRequestWithSameLogic(
                        "1", "3", "3",
                        2, false, "0",
                        FIRST_ANSWER,
                        SECOND_ANSWER,
                        THIRD_ANSWER,
                        FOURTH_ANSWER,
                        FIFTH_ANSWER),
                createEssayQuestionSuccessRequest("2", "3", false),
                createEssayQuestionSuccessRequest("3", "0", true)
        );
    }
}