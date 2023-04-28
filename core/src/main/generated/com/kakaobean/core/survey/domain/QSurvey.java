package com.kakaobean.core.survey.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurvey is a Querydsl query type for Survey
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurvey extends EntityPathBase<Survey> {

    private static final long serialVersionUID = -247496169L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurvey survey = new QSurvey("survey");

    public final com.kakaobean.core.common.domain.QBaseEntity _super = new com.kakaobean.core.common.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.kakaobean.core.survey.domain.question.Question, com.kakaobean.core.survey.domain.question.QQuestion> questions = this.<com.kakaobean.core.survey.domain.question.Question, com.kakaobean.core.survey.domain.question.QQuestion>createList("questions", com.kakaobean.core.survey.domain.question.Question.class, com.kakaobean.core.survey.domain.question.QQuestion.class, PathInits.DIRECT2);

    //inherited
    public final EnumPath<com.kakaobean.core.common.domain.BaseStatus> status = _super.status;

    public final QSurveyOwner surveyOwner;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QSurvey(String variable) {
        this(Survey.class, forVariable(variable), INITS);
    }

    public QSurvey(Path<? extends Survey> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSurvey(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSurvey(PathMetadata metadata, PathInits inits) {
        this(Survey.class, metadata, inits);
    }

    public QSurvey(Class<? extends Survey> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.surveyOwner = inits.isInitialized("surveyOwner") ? new QSurveyOwner(forProperty("surveyOwner")) : null;
    }

}

