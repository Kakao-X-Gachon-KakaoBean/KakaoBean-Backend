package com.kakaobean.response;

import com.kakaobean.core.response.application.ResponseService;
import com.kakaobean.core.response.application.dto.response.RegisterSurveyResponseSubmmitDto;
import com.kakaobean.response.dto.request.RegisterSurveyResponseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;

    @PostMapping
    public ResponseEntity resisterSurveyResponse(@AuthenticationPrincipal Long memberId,
                                                 @RequestBody @Validated RegisterSurveyResponseRequest request){
        RegisterSurveyResponseSubmmitDto res = responseService.registerSurveyResponse(request.toServiceDto(memberId));
        return new ResponseEntity(res, HttpStatus.OK);
    }

}
