package com.kakaobean.survey;

import com.kakaobean.common.dto.CommandSuccessResponse;
import com.kakaobean.core.survey.application.SurveyProvider;
import com.kakaobean.core.survey.application.SurveyService;
import com.kakaobean.core.survey.application.dto.response.*;
import com.kakaobean.survey.dto.request.RegisterSurveyRequest;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Timed("api.survey")
@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;
    private final SurveyProvider surveyProvider;

    @PostMapping
    public ResponseEntity registerSurvey(@AuthenticationPrincipal Long memberId,
                                         @RequestBody @Validated RegisterSurveyRequest request) {
        log.info("설문 생성 api 호출");
        RegisterSurveyResponseDto res = surveyService.registerSurvey(request.toServiceDto(memberId));
        log.info("설문 생성 api 종료");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity findSurvey(@PathVariable Long surveyId) {
        log.info("설문 조회 api 호출");
        FindSurveyResponseDto res = surveyProvider.getSurvey(surveyId);
        log.info("설문 조회 api 종료");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/own-survey")
    public ResponseEntity findOwnSurvey(@AuthenticationPrincipal Long memberId) {
        log.info("본인이 만든 설문 조회 api 호출");
        FindOwnSurveyListResponseDto res = surveyProvider.getOwnSurvey(memberId);
        log.info("본인이 만든 설문 조회 api 종료");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/submitted-survey")
    public ResponseEntity findSubmittedSurvey(@AuthenticationPrincipal Long memberId){
        log.info("본인이 참여한 설문 조회 api 호출");
        FindSubmittedSurveyListResponseDto res = surveyProvider.getSubmittedSurvey(memberId);
        log.info("본인이 참여한 설문 조회 api 종료");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @DeleteMapping("/{surveyId}")
    public ResponseEntity removeSurvey(@AuthenticationPrincipal Long memberId,
                                       @PathVariable Long surveyId){
        log.info("설문 삭제 api 호출");
        surveyService.removeSurvey(memberId, surveyId);
        log.info("설문 삭제 api 종료");
        return new ResponseEntity(new CommandSuccessResponse(), HttpStatus.OK);
    }

    @PatchMapping("/{surveyId}")
    public ResponseEntity closeSurvey(@AuthenticationPrincipal Long memberId,
                                      @PathVariable Long surveyId){
        log.info("설문 마감 api 호출");
        CloseSurveyResponseDto res = surveyService.closeSurvey(memberId, surveyId);
        log.info("설문 마감 api 종료");
        return new ResponseEntity(res, HttpStatus.OK);
    }

}