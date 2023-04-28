package com.kakaobean.core.survey.domain.question.essay;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEssayQuestion is a Querydsl query type for EssayQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEssayQuestion extends EntityPathBase<EssayQuestion> {

    private static final long serialVersionUID = 205188231L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEssayQuestion essayQuestion = new QEssayQuestion("essayQuestion");

    public final com.kakaobean.core.survey.domain.question.QQuestion _super;

    //inherited
    public final StringPath createdAt;

    //inherited
    public final StringPath explanation;

    //inherited
    public final BooleanPath finalQuestion;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final com.kakaobean.core.survey.domain.question.QQuestion nextQuestion;

    //inherited
    public final StringPath questionNumber;

    //inherited
    public final EnumPath<com.kakaobean.core.common.domain.BaseStatus> status;

    // inherited
    public final com.kakaobean.core.survey.domain.QSurvey survey;

    //inherited
    public final StringPath title;

    //inherited
    public final StringPath updatedAt;

    public QEssayQuestion(String variable) {
        this(EssayQuestion.class, forVariable(variable), INITS);
    }

    public QEssayQuestion(Path<? extends EssayQuestion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEssayQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEssayQuestion(PathMetadata metadata, PathInits inits) {
        this(EssayQuestion.class, metadata, inits);
    }

    public QEssayQuestion(Class<? extends EssayQuestion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.kakaobean.core.survey.domain.question.QQuestion(type, metadata, inits);
        this.createdAt = _super.createdAt;
        this.explanation = _super.explanation;
        this.finalQuestion = _super.finalQuestion;
        this.id = _super.id;
        this.nextQuestion = _super.nextQuestion;
        this.questionNumber = _super.questionNumber;
        this.status = _super.status;
        this.survey = _super.survey;
        this.title = _super.title;
        this.updatedAt = _super.updatedAt;
    }

}

