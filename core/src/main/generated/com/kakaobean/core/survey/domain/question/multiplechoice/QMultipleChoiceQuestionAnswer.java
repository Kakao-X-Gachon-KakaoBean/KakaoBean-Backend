package com.kakaobean.core.survey.domain.question.multiplechoice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMultipleChoiceQuestionAnswer is a Querydsl query type for MultipleChoiceQuestionAnswer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMultipleChoiceQuestionAnswer extends EntityPathBase<MultipleChoiceQuestionAnswer> {

    private static final long serialVersionUID = 642913415L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMultipleChoiceQuestionAnswer multipleChoiceQuestionAnswer = new QMultipleChoiceQuestionAnswer("multipleChoiceQuestionAnswer");

    public final com.kakaobean.core.common.domain.QBaseEntity _super = new com.kakaobean.core.common.domain.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMultipleChoiceQuestion question;

    //inherited
    public final EnumPath<com.kakaobean.core.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QMultipleChoiceQuestionAnswer(String variable) {
        this(MultipleChoiceQuestionAnswer.class, forVariable(variable), INITS);
    }

    public QMultipleChoiceQuestionAnswer(Path<? extends MultipleChoiceQuestionAnswer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMultipleChoiceQuestionAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMultipleChoiceQuestionAnswer(PathMetadata metadata, PathInits inits) {
        this(MultipleChoiceQuestionAnswer.class, metadata, inits);
    }

    public QMultipleChoiceQuestionAnswer(Class<? extends MultipleChoiceQuestionAnswer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question = inits.isInitialized("question") ? new QMultipleChoiceQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

