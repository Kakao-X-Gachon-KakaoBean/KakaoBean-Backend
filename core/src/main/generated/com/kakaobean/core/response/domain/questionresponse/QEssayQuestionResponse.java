package com.kakaobean.core.response.domain.questionresponse;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEssayQuestionResponse is a Querydsl query type for EssayQuestionResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEssayQuestionResponse extends EntityPathBase<EssayQuestionResponse> {

    private static final long serialVersionUID = 1666576833L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEssayQuestionResponse essayQuestionResponse = new QEssayQuestionResponse("essayQuestionResponse");

    public final QQuestionResponse _super;

    public final StringPath answer = createString("answer");

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final NumberPath<Long> questionId;

    // inherited
    public final com.kakaobean.core.response.domain.QSurveyResponse surveyResponse;

    public QEssayQuestionResponse(String variable) {
        this(EssayQuestionResponse.class, forVariable(variable), INITS);
    }

    public QEssayQuestionResponse(Path<? extends EssayQuestionResponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEssayQuestionResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEssayQuestionResponse(PathMetadata metadata, PathInits inits) {
        this(EssayQuestionResponse.class, metadata, inits);
    }

    public QEssayQuestionResponse(Class<? extends EssayQuestionResponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QQuestionResponse(type, metadata, inits);
        this.id = _super.id;
        this.questionId = _super.questionId;
        this.surveyResponse = _super.surveyResponse;
    }

}

