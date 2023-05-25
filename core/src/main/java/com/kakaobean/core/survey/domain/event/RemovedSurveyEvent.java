package com.kakaobean.core.survey.domain.event;

import com.kakaobean.core.common.event.Event;
import lombok.Getter;

@Getter
public class RemovedSurveyEvent extends Event {

    private final Long surveyId;

    public RemovedSurveyEvent(Long surveyId) {
        this.surveyId = surveyId;
    }
}
