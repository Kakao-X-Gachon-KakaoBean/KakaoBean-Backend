package com.kakaobean.core.response.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurveyResponse is a Querydsl query type for SurveyResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyResponse extends EntityPathBase<SurveyResponse> {

    private static final long serialVersionUID = 1416106847L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurveyResponse surveyResponse = new QSurveyResponse("surveyResponse");

    public final com.kakaobean.core.common.domain.QBaseEntity _super = new com.kakaobean.core.common.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.kakaobean.core.response.domain.questionresponse.QuestionResponse, com.kakaobean.core.response.domain.questionresponse.QQuestionResponse> questionResponses = this.<com.kakaobean.core.response.domain.questionresponse.QuestionResponse, com.kakaobean.core.response.domain.questionresponse.QQuestionResponse>createList("questionResponses", com.kakaobean.core.response.domain.questionresponse.QuestionResponse.class, com.kakaobean.core.response.domain.questionresponse.QQuestionResponse.class, PathInits.DIRECT2);

    public final QRespondent respondent;

    //inherited
    public final EnumPath<com.kakaobean.core.common.domain.BaseStatus> status = _super.status;

    public final NumberPath<Long> surveyId = createNumber("surveyId", Long.class);

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QSurveyResponse(String variable) {
        this(SurveyResponse.class, forVariable(variable), INITS);
    }

    public QSurveyResponse(Path<? extends SurveyResponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSurveyResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSurveyResponse(PathMetadata metadata, PathInits inits) {
        this(SurveyResponse.class, metadata, inits);
    }

    public QSurveyResponse(Class<? extends SurveyResponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.respondent = inits.isInitialized("respondent") ? new QRespondent(forProperty("respondent")) : null;
    }

}

