package com.kakaobean.core.response.domain.questionresponse;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionResponse is a Querydsl query type for QuestionResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionResponse extends EntityPathBase<QuestionResponse> {

    private static final long serialVersionUID = -439089334L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionResponse questionResponse = new QQuestionResponse("questionResponse");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> questionId = createNumber("questionId", Long.class);

    public final com.kakaobean.core.response.domain.QSurveyResponse surveyResponse;

    public QQuestionResponse(String variable) {
        this(QuestionResponse.class, forVariable(variable), INITS);
    }

    public QQuestionResponse(Path<? extends QuestionResponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionResponse(PathMetadata metadata, PathInits inits) {
        this(QuestionResponse.class, metadata, inits);
    }

    public QQuestionResponse(Class<? extends QuestionResponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.surveyResponse = inits.isInitialized("surveyResponse") ? new com.kakaobean.core.response.domain.QSurveyResponse(forProperty("surveyResponse"), inits.get("surveyResponse")) : null;
    }

}

