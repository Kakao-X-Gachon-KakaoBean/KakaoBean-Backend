package com.kakaobean.core.response.application;

import com.kakaobean.core.response.domain.SurveyResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResponseService {

    private final SurveyResponseRepository surveyResponseRepository;

    @Transactional
    public Long registerSurveyResponse(){
        return null;
    }
}
