package com.kakaobean.response;

import com.kakaobean.core.response.application.ResponseProvider;
import com.kakaobean.core.response.application.ResponseService;
import com.kakaobean.core.response.application.dto.response.FindResponsesDto;
import com.kakaobean.core.response.application.dto.response.FindSurveyStatisticsResponseDto;
import com.kakaobean.core.response.application.dto.response.RegisterSurveyResponseSubmmitDto;
import com.kakaobean.response.dto.request.RegisterSurveyResponseRequest;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Timed("api.response")
@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;
    private final ResponseProvider responseProvider;

    @PostMapping
    public ResponseEntity resisterSurveyResponse(@AuthenticationPrincipal Long memberId,
                                                 @RequestBody @Validated RegisterSurveyResponseRequest request){
        log.info("설문 응답 생성 api 호출");
        RegisterSurveyResponseSubmmitDto res = responseService.registerSurveyResponse(request.toServiceDto(memberId));
        log.info("설문 응답 생성 api 종료");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity finResponses(@AuthenticationPrincipal Long memberId,
                                       @PathVariable Long surveyId){
        log.info("응답 조회 api 호출");
        FindResponsesDto res = responseProvider.findResponses(memberId, surveyId);
        log.info("응답 조회 api 종료");
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("survey-statistics/{surveyId}")
    public ResponseEntity findSurveyStatistics(@AuthenticationPrincipal Long memberId,
                                               @PathVariable Long surveyId){
        log.info("응답 통계 조회 api 호출");
        FindSurveyStatisticsResponseDto res = responseProvider.findSurveyStatistics(memberId,surveyId);
        log.info("응답 통계 조회 api 종료");
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
