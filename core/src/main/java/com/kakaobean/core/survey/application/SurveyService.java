package com.kakaobean.core.survey.application;


import com.kakaobean.core.survey.application.dto.request.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.RemoveSurveyResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.domain.SurveyValidator;
import com.kakaobean.core.survey.exception.NotExistsSurveyException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;
    private final SurveyValidator surveyValidator;

    public RegisterSurveyResponseDto registerSurvey(RegisterSurveyRequestDto dto){
        Survey survey = surveyMapper.mapFrom(dto);
        survey.place(surveyValidator);
        Survey saveSurvey = surveyRepository.save(survey);
        return new RegisterSurveyResponseDto(saveSurvey.getId());
    }

    public void removeSurvey(Long memberId, Long surveyId){
        Survey survey = surveyRepository.findSurveyBySurveyIdAndOwnerId(surveyId, memberId)
                .orElseThrow(NotExistsSurveyException::new);
        survey.remove();
    }
}
