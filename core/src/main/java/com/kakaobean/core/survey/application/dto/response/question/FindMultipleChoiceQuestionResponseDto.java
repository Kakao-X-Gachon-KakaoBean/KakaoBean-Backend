package com.kakaobean.core.survey.application.dto.response.question;

import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.kakaobean.core.survey.application.dto.QuestionDTOType.*;

@Getter
@NoArgsConstructor
public class FindMultipleChoiceQuestionResponseDto extends FindQuestionResponseDto {

    private Integer numberOfAnswerChoices;
    private List<FindMultipleChoiceQuestionAnswerDto> answers;
    private List<FindQuestionFlowLogicResponseDto> logics;

    @Builder
    public FindMultipleChoiceQuestionResponseDto(Long questionId,
                                                 String title,
                                                 String explanation,
                                                 String questionNumber,
                                                 Boolean finalQuestion,
                                                 String nextQuestionNumber,
                                                 Integer numberOfAnswerChoices,
                                                 List<FindMultipleChoiceQuestionAnswerDto> answers,
                                                 List<FindQuestionFlowLogicResponseDto> logics) {
        super(questionId, title, explanation, questionNumber, finalQuestion, nextQuestionNumber, MULTIPLE);
        this.numberOfAnswerChoices = numberOfAnswerChoices;
        this.answers = answers;
        this.logics = logics;
    }

}
