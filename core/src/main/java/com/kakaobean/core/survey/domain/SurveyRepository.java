package com.kakaobean.core.survey.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query("select s from Survey s where s.surveyOwner.memberId = :memberId and s.status = 'ACTIVE'")
    List<Survey> findSurveyByMemberId(Long memberId);
}
