package com.kakaobean.core.response.application.dto.response.statistics;

import com.kakaobean.core.survey.domain.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class QuestionResultDto {

    private String type;

    private String title;

    private String explanation;

    private Integer min;

    private Integer max;

    private List<AnswerResultDto> answers;

    public QuestionResultDto(String type,
                             String title,
                             String explanation,
                             Integer min,
                             Integer max,
                             List<AnswerResultDto> answers) {
        this.type = type;
        this.title = title;
        this.explanation = explanation;
        this.min = min;
        this.max = max;
        this.answers = answers;
    }

    public static List<QuestionResultDto> from(List<Question> questions){


    }

}
