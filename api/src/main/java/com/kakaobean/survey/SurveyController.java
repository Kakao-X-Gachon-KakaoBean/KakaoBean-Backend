package com.kakaobean.survey;

import com.kakaobean.core.survey.application.SurveyProvider;
import com.kakaobean.core.survey.application.SurveyService;
import com.kakaobean.core.survey.application.dto.response.FindSurveyResponseDto;
import com.kakaobean.core.survey.application.dto.response.RegisterSurveyResponseDto;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyController{

    private final SurveyService surveyService;
    private final SurveyProvider surveyProvider;

    @PostMapping
    public ResponseEntity registerSurvey(@AuthenticationPrincipal Long memberId,
                                         @RequestBody @Validated  RegisterSurveyRequest request){
        RegisterSurveyResponseDto res = surveyService.registerSurvey(request.toServiceDto(memberId));
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity getSurvey(@PathVariable Long surveyId){
        FindSurveyResponseDto res = surveyProvider.getSurvey(surveyId);
        return new ResponseEntity(res, HttpStatus.OK);
    }

}