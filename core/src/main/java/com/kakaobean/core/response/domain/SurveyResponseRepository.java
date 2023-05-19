package com.kakaobean.core.response.domain;

import com.kakaobean.core.response.domain.SurveyResponse;
import com.kakaobean.core.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {

    @Query("select count(r) from survey_response r where r.surveyId = :surveyId and r.status = 'ACTIVE'")
    Integer getNumberOfResponseBySurveyId(Long surveyId);

}
