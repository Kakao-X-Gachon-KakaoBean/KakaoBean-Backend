package com.kakaobean.core.survey.application;

import com.kakaobean.core.survey.application.dto.RegisterSurveyRequestDto;
import com.kakaobean.core.survey.domain.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;

    @Transactional
    public Long registerSurvey(RegisterSurveyRequestDto dto){
        return null;
    }

}
