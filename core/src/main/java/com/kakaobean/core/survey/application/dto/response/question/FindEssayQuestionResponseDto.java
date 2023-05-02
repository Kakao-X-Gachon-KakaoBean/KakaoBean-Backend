package com.kakaobean.core.survey.application.dto.response.question;

import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kakaobean.core.survey.application.dto.QuestionDTOType.*;

@Getter
@NoArgsConstructor
public class FindEssayQuestionResponseDto extends FindQuestionResponseDto {

    @Builder
    public FindEssayQuestionResponseDto(Long questionId,
                                        String title,
                                        String explanation,
                                        String questionNumber,
                                        Boolean finalQuestion,
                                        String nextQuestionNumber) {
        super(questionId, title, explanation, questionNumber, finalQuestion, nextQuestionNumber, ESSAY);
    }

}
