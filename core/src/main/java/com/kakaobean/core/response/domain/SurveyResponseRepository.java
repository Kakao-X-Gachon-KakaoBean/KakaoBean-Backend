package com.kakaobean.core.response.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {

    @Query("select count(r) from survey_response r where r.surveyId = :surveyId and r.status = 'ACTIVE'")
    Optional<Integer> getNumberOfResponseBySurveyId(Long surveyId);

    @Query("select r from survey_response r where r.respondent.memberId = :memberId and r.status = 'ACTIVE'")
    List<SurveyResponse> findSurveyResponseByMemberId(Long memberId);

    // 아직 미완
    @Query("select r from survey_response r where r.surveyId = :surveyId and r.status = 'ACTIVE'")
    List<SurveyResponse> findSurveyResponseBySurveyId(Long surveyId);

    @Modifying
    @Query("update survey_response r set r.status = 'INACTIVE' where r.surveyId = :surveyId and r.status = 'ACTIVE'")
    void deleteAllBySurveyId(Long surveyId);

}
