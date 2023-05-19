package com.kakaobean.core.survey.domain;

import com.kakaobean.core.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query("select s from Survey s where s.surveyOwner.memberId = :memberId and s.status = 'ACTIVE'")
    List<Survey> findSurveyByMemberId(Long memberId);
}
