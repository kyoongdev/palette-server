package com.study.palette.module.mixMastering.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMixMasteringGenre is a Querydsl query type for MixMasteringGenre
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMixMasteringGenre extends EntityPathBase<MixMasteringGenre> {

    private static final long serialVersionUID = 1792120001L;

    public static final QMixMasteringGenre mixMasteringGenre = new QMixMasteringGenre("mixMasteringGenre");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final StringPath userId = createString("userId");

    public QMixMasteringGenre(String variable) {
        super(MixMasteringGenre.class, forVariable(variable));
    }

    public QMixMasteringGenre(Path<? extends MixMasteringGenre> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMixMasteringGenre(PathMetadata metadata) {
        super(MixMasteringGenre.class, metadata);
    }

}

