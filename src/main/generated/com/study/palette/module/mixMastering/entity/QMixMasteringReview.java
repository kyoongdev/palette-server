package com.study.palette.module.mixMastering.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMixMasteringReview is a Querydsl query type for MixMasteringReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMixMasteringReview extends EntityPathBase<MixMasteringReview> {

    private static final long serialVersionUID = 36295642L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMixMasteringReview mixMasteringReview = new QMixMasteringReview("mixMasteringReview");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final QMixMasteringInfo mixMasteringInfo;

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final StringPath review = createString("review");

    public final StringPath userId = createString("userId");

    public QMixMasteringReview(String variable) {
        this(MixMasteringReview.class, forVariable(variable), INITS);
    }

    public QMixMasteringReview(Path<? extends MixMasteringReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMixMasteringReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMixMasteringReview(PathMetadata metadata, PathInits inits) {
        this(MixMasteringReview.class, metadata, inits);
    }

    public QMixMasteringReview(Class<? extends MixMasteringReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mixMasteringInfo = inits.isInitialized("mixMasteringInfo") ? new QMixMasteringInfo(forProperty("mixMasteringInfo"), inits.get("mixMasteringInfo")) : null;
    }

}

