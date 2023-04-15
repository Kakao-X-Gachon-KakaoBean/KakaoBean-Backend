package com.kakaobean.core.survey.application;

import com.kakaobean.core.survey.application.dto.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.RegisterSurveyResponseDto;
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
        surveyValidator.validate(survey);
        surveyRepository.save(survey);
        return new RegisterSurveyResponseDto(1L);
    }
}
