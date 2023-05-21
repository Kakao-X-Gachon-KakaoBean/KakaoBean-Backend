package com.kakaobean.core.response.application;

import com.kakaobean.core.response.application.dto.request.RegisterSurveyResponseRequestDto;
import com.kakaobean.core.response.application.dto.response.RegisterSurveyResponseSubmmitDto;
import com.kakaobean.core.response.domain.SurveyResponse;
import com.kakaobean.core.response.domain.SurveyResponseRepository;
import com.kakaobean.core.survey.domain.SurveyRepository;
import com.kakaobean.core.survey.exception.NotExistsSurveyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResponseService {

    private final SurveyResponseRepository surveyResponseRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyResponseMapper surveyResponseMapper;

    @Transactional
    public RegisterSurveyResponseSubmmitDto registerSurveyResponse(RegisterSurveyResponseRequestDto dto){

        // 먼저 응답 생성할 때 해당 설문이 존재하지 않으면 예외 날리도록
        surveyRepository.findById(dto.getSurveyId()).orElseThrow(NotExistsSurveyException::new);
        SurveyResponse surveyResponse = surveyResponseMapper.mapForm(dto);
        SurveyResponse saveSurveyResponse = surveyResponseRepository.save(surveyResponse);
        return new RegisterSurveyResponseSubmmitDto(saveSurveyResponse.getId());
    }
}
