package com.study.palette.module.mixMastering.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMixMasteringInfo is a Querydsl query type for MixMasteringInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMixMasteringInfo extends EntityPathBase<MixMasteringInfo> {

    private static final long serialVersionUID = 750614960L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMixMasteringInfo mixMasteringInfo = new QMixMasteringInfo("mixMasteringInfo");

    public final StringPath artistId = createString("artistId");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath editInfo = createString("editInfo");

    public final StringPath id = createString("id");

    public final ListPath<MixMasteringFile, QMixMasteringFile> mixMasteringFile = this.<MixMasteringFile, QMixMasteringFile>createList("mixMasteringFile", MixMasteringFile.class, QMixMasteringFile.class, PathInits.DIRECT2);

    public final QMixMasteringGenre mixMasteringGenre;

    public final ListPath<MixMasteringLicenseInfo, QMixMasteringLicenseInfo> mixMasteringLicenseInfo = this.<MixMasteringLicenseInfo, QMixMasteringLicenseInfo>createList("mixMasteringLicenseInfo", MixMasteringLicenseInfo.class, QMixMasteringLicenseInfo.class, PathInits.DIRECT2);

    public final ListPath<MixMasteringReview, QMixMasteringReview> mixMasteringReview = this.<MixMasteringReview, QMixMasteringReview>createList("mixMasteringReview", MixMasteringReview.class, QMixMasteringReview.class, PathInits.DIRECT2);

    public final StringPath serviceExplain = createString("serviceExplain");

    public final StringPath serviceName = createString("serviceName");

    public final BooleanPath serviceStatus = createBoolean("serviceStatus");

    public final StringPath userId = createString("userId");

    public QMixMasteringInfo(String variable) {
        this(MixMasteringInfo.class, forVariable(variable), INITS);
    }

    public QMixMasteringInfo(Path<? extends MixMasteringInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMixMasteringInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMixMasteringInfo(PathMetadata metadata, PathInits inits) {
        this(MixMasteringInfo.class, metadata, inits);
    }

    public QMixMasteringInfo(Class<? extends MixMasteringInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mixMasteringGenre = inits.isInitialized("mixMasteringGenre") ? new QMixMasteringGenre(forProperty("mixMasteringGenre")) : null;
    }

}

