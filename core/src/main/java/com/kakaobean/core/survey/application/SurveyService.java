package com.kakaobean.core.survey.application;

import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.domain.SurveyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;
    private final SurveyValidator surveyValidator;

    @Transactional
    public RegisterSurveyResponseDto registerSurvey(RegisterSurveyRequestDto dto){
        Survey survey = surveyMapper.mapFrom(dto);
        survey.place(surveyValidator);
        Survey saveSurvey = surveyRepository.save(survey);
        return new RegisterSurveyResponseDto(saveSurvey.getId());
    }
}
