package com.kakaobean.core.response.application;

import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;
import com.kakaobean.core.response.application.dto.response.RegisterSurveyResponseSubmmitDto;
import com.kakaobean.core.response.domain.SurveyResponse;
import com.kakaobean.core.response.domain.SurveyResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResponseService {

    private final SurveyResponseRepository surveyResponseRepository;
    private final SurveyResponseMapper surveyResponseMapper;

    @Transactional
    public RegisterSurveyResponseSubmmitDto registerSurveyResponse(RegisterSurveyResponseRequestDto dto){
        SurveyResponse surveyResponse = surveyResponseMapper.mapForm(dto);
        SurveyResponse saveSurveyResponse = surveyResponseRepository.save(surveyResponse);
        return new RegisterSurveyResponseSubmmitDto(saveSurveyResponse.getId());
    }
}
