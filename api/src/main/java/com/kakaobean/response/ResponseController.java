package com.kakaobean.response;

import com.kakaobean.core.response.application.ResponseService;
import com.kakaobean.response.dto.ResgisterSurveyResponseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;

//    @PostMapping("/{surveyId}/rseponse")
//    public ResponseEntity resisterSurveyResponse(@AuthenticationPrincipal Long memberId,
//                                           @PathVariable Long surveyId,
//                                           @RequestBody ResgisterSurveyResponseRequest request){
//        ResisterSurveyResponseResponseDto res = responseService.registerSurveyResponse(request.toServiceDto(memberId, surveyId));
//        return ResponseEntity(res, HttpStatus.OK);
//    }

}
