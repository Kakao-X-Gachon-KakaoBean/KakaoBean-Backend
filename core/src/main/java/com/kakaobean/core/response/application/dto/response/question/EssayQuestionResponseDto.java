package com.kakaobean.core.response.application.dto.response.question;

import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EssayQuestionResponseDto extends QuestionResponseDto {

    private String answer;

    public EssayQuestionResponseDto(Long questionId, QuestionDTOType questionType, String answer) {
        super(questionId, questionType);
        this.answer = answer;
    }

    public EssayQuestionResponseDto(Long questionId, QuestionDTOType type, String title, String answer) {
        super(questionId, type, title);
        this.answer = answer;
    }
}
