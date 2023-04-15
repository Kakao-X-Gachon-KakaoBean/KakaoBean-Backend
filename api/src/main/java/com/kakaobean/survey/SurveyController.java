package com.kakaobean.survey;

import com.kakaobean.core.survey.application.SurveyService;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyController{

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity registerSurvey(@AuthenticationPrincipal Long memberId, @RequestBody RegisterSurveyRequest request){
        surveyService.registerSurvey(request.toServiceDto(memberId));
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
