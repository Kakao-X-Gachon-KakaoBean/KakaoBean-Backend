package com.kakaobean.core.response.application.dto.request.questionresponse;

import com.kakaobean.core.response.domain.questionresponse.MultipleChoiceQuestionResponse;
import com.kakaobean.core.response.domain.questionresponse.QuestionResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RegisterMultipleChoiceQuestionResponseRequestDto extends RegisterQuestionResponseRequestDto{

    private List<RegisterMultipleChoiceAnswerResponseRequestDto> answers;

    @Builder
    public RegisterMultipleChoiceQuestionResponseRequestDto(Long questionId,
                                                            List<RegisterMultipleChoiceAnswerResponseRequestDto> answers) {
        super(questionId);
        this.answers = answers;
    }

    @Override
    protected QuestionResponse createdDetailQuestionResponseEntity() {
        return new MultipleChoiceQuestionResponse(
                questionId,
                answers.stream()
                        .map(answer -> answer.toEntity())
                        .collect(Collectors.toList())
        );
    }
}
