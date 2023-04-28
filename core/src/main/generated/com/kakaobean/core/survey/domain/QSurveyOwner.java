package com.kakaobean.core.survey.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSurveyOwner is a Querydsl query type for SurveyOwner
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSurveyOwner extends BeanPath<SurveyOwner> {

    private static final long serialVersionUID = -903460L;

    public static final QSurveyOwner surveyOwner = new QSurveyOwner("surveyOwner");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public QSurveyOwner(String variable) {
        super(SurveyOwner.class, forVariable(variable));
    }

    public QSurveyOwner(Path<? extends SurveyOwner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSurveyOwner(PathMetadata metadata) {
        super(SurveyOwner.class, metadata);
    }

}

