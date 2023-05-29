package com.kakaobean.core.response.application.dto.response.question;

import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MultipleChoiceQuestionResponseDto extends QuestionResponseDto{

    private List<String> answers;

    public MultipleChoiceQuestionResponseDto(Long questionId, QuestionDTOType questionType, List<String> answers) {
        super(questionId, questionType);
        this.answers = answers;
    }
}
