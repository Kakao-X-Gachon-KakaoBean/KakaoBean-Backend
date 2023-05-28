package com.kakaobean.core.response.infrastructure;

import com.kakaobean.core.response.application.dto.response.SurveyResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.kakaobean.core.member.domain.QMember.*;
import static com.kakaobean.core.response.domain.QSurveyResponse.*;
import static com.kakaobean.core.response.domain.questionresponse.QQuestionResponse.*;

@Repository
@RequiredArgsConstructor
public class ResponseQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<SurveyResponseDto> findResponses(Long surevyId){
        List<QuerydslResponsesDto> list = findResponseEntityAndMemberInfo(surevyId);
        return list.stream()
                .map(SurveyResponseDto::from)
                .collect(Collectors.toList());
    }

    private List<QuerydslResponsesDto> findResponseEntityAndMemberInfo(Long surveyId) {
        return queryFactory
                .select(Projections.fields(
                        QuerydslResponsesDto.class,
                        member.gender,
                        member.age,
                        member.auth.email,
                        member.name,
                        surveyResponse
                ))
                .from(surveyResponse)
                .join(member).on(member.id.eq(surveyResponse.respondent.memberId))
                .join(surveyResponse.questionResponses, questionResponse).fetchJoin()
                .where(surveyResponse.surveyId.eq(surveyId))
                .fetch();
    }
}
