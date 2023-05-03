package com.kakaobean.core.response.application.dto.request;

import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterQuestionResponseRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterSurveyResponseRequestDto {

    private Long memberId;
    private Long surveyId;
    private List<RegisterQuestionResponseRequestDto> dtoList = new ArrayList<>();

    public RegisterSurveyResponseRequestDto(Long memberId,
                                            Long surveyId,
                                            List<RegisterQuestionResponseRequestDto> dtoList) {
        this.memberId = memberId;
        this.surveyId = surveyId;
        this.dtoList = dtoList;
    }
}
