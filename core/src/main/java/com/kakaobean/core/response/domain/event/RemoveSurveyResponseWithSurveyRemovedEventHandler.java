package com.kakaobean.core.response.domain.event;

import com.kakaobean.core.response.application.ResponseService;
import com.kakaobean.core.survey.domain.event.RemovedSurveyEvent;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class RemoveSurveyResponseWithSurveyRemovedEventHandler {

    private final ResponseService responseService;

    @Async
    @TransactionalEventListener(RemovedSurveyEvent.class)
    public void handle(RemovedSurveyEvent event){
        responseService.removeSurveyResponsesWithEvent(event.getSurveyId());
    }
}
