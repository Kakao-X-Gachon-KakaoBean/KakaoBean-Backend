package com.kakaobean.core.survey.application.dto.question;

import com.kakaobean.core.survey.domain.question.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class GetRangeQuestionResponseDto extends GetQuestionResponseDto{

    private Integer min;
    private Integer max;

    @Builder
    public GetRangeQuestionResponseDto(String title,
                                       String explanation,
                                       String questionNumber,
                                       Boolean finalQuestion,
                                       Question nextQuestion,
                                       Integer min,
                                       Integer max) {
        super(title, explanation, questionNumber, finalQuestion, nextQuestion.getQuestionNumber());
        this.min = min;
        this.max = max;
    }

    public void isNull(Question nextQuestion){
        if (nextQuestion!=null){
            //super(nextQuestion.getQuestionNumber());
        }
    }

}
