package com.kakaobean.core.survey.application;

import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.exception.NotExistsSurveyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyProvider {

    private final SurveyRepository surveyRepository;

    public FindSurveyResponseDto getSurvey(Long surveyId) {
        Survey findSurvey = surveyRepository.findById(surveyId).orElseThrow(NotExistsSurveyException::new);
        return FindSurveyResponseDto.from(findSurvey);
    }
}
