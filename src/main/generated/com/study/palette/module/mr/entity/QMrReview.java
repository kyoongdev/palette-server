package com.study.palette.module.mr.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMrReview is a Querydsl query type for MrReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMrReview extends EntityPathBase<MrReview> {

    private static final long serialVersionUID = -846905444L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMrReview mrReview = new QMrReview("mrReview");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final QMrInfo mrInfo;

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final StringPath review = createString("review");

    public final StringPath userId = createString("userId");

    public QMrReview(String variable) {
        this(MrReview.class, forVariable(variable), INITS);
    }

    public QMrReview(Path<? extends MrReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMrReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMrReview(PathMetadata metadata, PathInits inits) {
        this(MrReview.class, metadata, inits);
    }

    public QMrReview(Class<? extends MrReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mrInfo = inits.isInitialized("mrInfo") ? new QMrInfo(forProperty("mrInfo"), inits.get("mrInfo")) : null;
    }

}

