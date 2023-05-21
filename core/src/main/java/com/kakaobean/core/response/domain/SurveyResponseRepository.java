package com.kakaobean.core.response.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {

    @Query("select count(r) from survey_response r where r.surveyId = :surveyId and r.status = 'ACTIVE'")
    Integer getNumberOfResponseBySurveyId(Long surveyId);

    @Query("select r from survey_response r where r.respondent.memberId = :memberId and r.status = 'ACTIVE'")
    List<SurveyResponse> findSurveyResponseByMemberId(Long memberId);
}
