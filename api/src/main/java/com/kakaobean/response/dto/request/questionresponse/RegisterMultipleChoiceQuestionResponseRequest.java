package com.kakaobean.response.dto.request.questionresponse;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterMultipleChoiceQuestionResponseRequestDto;
import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterQuestionResponseRequestDto;
import com.kakaobean.core.survey.application.dto.QuestionDTOType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class RegisterMultipleChoiceQuestionResponseRequest extends RegisterQuestionResponseRequest{

    private List<RegisterMutipleChoiceAnswerResponseRequest> answers;

    public RegisterMultipleChoiceQuestionResponseRequest(QuestionDTOType type,
                                                         Long questionId,
                                                         List<RegisterMutipleChoiceAnswerResponseRequest> answers) {
        super(type, questionId);
        this.answers = answers;
    }

    @Override
    protected RegisterQuestionResponseRequestDto createDetailServiceDto() {
        return new RegisterMultipleChoiceQuestionResponseRequestDto(
                questionId,
                answers.stream()
                        .map(answer -> answer.toServiceDto())
                        .collect(Collectors.toList())
        );
    }
}
