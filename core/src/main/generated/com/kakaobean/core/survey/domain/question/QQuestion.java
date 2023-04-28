package com.kakaobean.core.survey.domain.question;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -1368451503L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final com.kakaobean.core.common.domain.QBaseEntity _super = new com.kakaobean.core.common.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final StringPath explanation = createString("explanation");

    public final BooleanPath finalQuestion = createBoolean("finalQuestion");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QQuestion nextQuestion;

    public final StringPath questionNumber = createString("questionNumber");

    //inherited
    public final EnumPath<com.kakaobean.core.common.domain.BaseStatus> status = _super.status;

    public final com.kakaobean.core.survey.domain.QSurvey survey;

    public final StringPath title = createString("title");

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nextQuestion = inits.isInitialized("nextQuestion") ? new QQuestion(forProperty("nextQuestion"), inits.get("nextQuestion")) : null;
        this.survey = inits.isInitialized("survey") ? new com.kakaobean.core.survey.domain.QSurvey(forProperty("survey"), inits.get("survey")) : null;
    }

}

