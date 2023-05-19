package com.kakaobean.core.survey.application;

import com.kakaobean.core.response.domain.SurveyResponseRepository;
import com.kakaobean.core.survey.application.dto.response.FindOwnSurveyListResponseDto;
import com.kakaobean.core.survey.application.dto.response.FindOwnSurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.core.survey.domain.Survey;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.exception.NotExistsSurveyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyProvider {

    private final SurveyRepository surveyRepository;

    private final SurveyResponseRepository surveyResponseRepository;

    public FindSurveyResponseDto getSurvey(Long surveyId) {
        Survey findSurvey = surveyRepository.findById(surveyId).orElseThrow(NotExistsSurveyException::new);
        return FindSurveyResponseDto.from(findSurvey);
    }

    public FindOwnSurveyListResponseDto getOwnSurvey(Long memberId){
        List<Survey> myOwnSurveys = surveyRepository.findSurveyByMemberId(memberId);
        List<Integer> numberOfResponseEachSurveys = myOwnSurveys.stream()
                .map(survey -> surveyResponseRepository.getNumberOfResponseBySurveyId(survey.getId()))
                .collect(Collectors.toList());
        return FindOwnSurveyListResponseDto.from(myOwnSurveys, numberOfResponseEachSurveys);
    }
}
