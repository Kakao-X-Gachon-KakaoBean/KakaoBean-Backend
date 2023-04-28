package com.kakaobean.core.survey.domain.question.range;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRangeQuestion is a Querydsl query type for RangeQuestion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRangeQuestion extends EntityPathBase<RangeQuestion> {

    private static final long serialVersionUID = 715636231L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRangeQuestion rangeQuestion = new QRangeQuestion("rangeQuestion");

    public final com.kakaobean.core.survey.domain.question.QQuestion _super;

    //inherited
    public final StringPath createdAt;

    //inherited
    public final StringPath explanation;

    //inherited
    public final BooleanPath finalQuestion;

    //inherited
    public final NumberPath<Long> id;

    public final NumberPath<Integer> max = createNumber("max", Integer.class);

    public final NumberPath<Integer> min = createNumber("min", Integer.class);

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

    public QRangeQuestion(String variable) {
        this(RangeQuestion.class, forVariable(variable), INITS);
    }

    public QRangeQuestion(Path<? extends RangeQuestion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRangeQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRangeQuestion(PathMetadata metadata, PathInits inits) {
        this(RangeQuestion.class, metadata, inits);
    }

    public QRangeQuestion(Class<? extends RangeQuestion> type, PathMetadata metadata, PathInits inits) {
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

