package com.kakaobean.core.response.application;

import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;
import com.kakaobean.core.response.application.dto.request.questionresponse.RegisterQuestionResponseRequestDto;
import com.kakaobean.core.response.domain.Respondent;
import com.kakaobean.core.response.domain.SurveyResponse;
import com.kakaobean.core.response.domain.questionresponse.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SurveyResponseMapper {

    public SurveyResponse mapForm(RegisterSurveyResponseRequestDto dto) {
        SurveyResponse surveyResponse = new SurveyResponse(
                dto.getSurveyId(),
                new Respondent(dto.getMemberId()),
                createQuestionResponse(dto)
        );

        return surveyResponse;
    }

    public List<QuestionResponse> createQuestionResponse(RegisterSurveyResponseRequestDto dto){
        return dto.getDtoList()
                    .stream()
                    .map(questionResponse -> questionResponse.toEntity())
                    .collect(Collectors.toList());
    }

}
