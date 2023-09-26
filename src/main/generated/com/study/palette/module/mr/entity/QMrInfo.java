package com.study.palette.module.mr.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMrInfo is a Querydsl query type for MrInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMrInfo extends EntityPathBase<MrInfo> {

    private static final long serialVersionUID = 874835442L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMrInfo mrInfo = new QMrInfo("mrInfo");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath genre = createString("genre");

    public final StringPath id = createString("id");

    public final StringPath mood = createString("mood");

    public final ListPath<MrFile, QMrFile> mrFile = this.<MrFile, QMrFile>createList("mrFile", MrFile.class, QMrFile.class, PathInits.DIRECT2);

    public final ListPath<MrLicenseInfo, QMrLicenseInfo> mrLicenseInfo = this.<MrLicenseInfo, QMrLicenseInfo>createList("mrLicenseInfo", MrLicenseInfo.class, QMrLicenseInfo.class, PathInits.DIRECT2);

    public final ListPath<MrReview, QMrReview> mrReview = this.<MrReview, QMrReview>createList("mrReview", MrReview.class, QMrReview.class, PathInits.DIRECT2);

    public final NumberPath<Integer> musicLength = createNumber("musicLength", Integer.class);

    public final NumberPath<Integer> salesType = createNumber("salesType", Integer.class);

    public final StringPath serviceName = createString("serviceName");

    public final NumberPath<Integer> serviceStatus = createNumber("serviceStatus", Integer.class);

    public final com.study.palette.module.user.entity.QUserArtist userArtist;

    public final StringPath userId = createString("userId");

    public QMrInfo(String variable) {
        this(MrInfo.class, forVariable(variable), INITS);
    }

    public QMrInfo(Path<? extends MrInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMrInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMrInfo(PathMetadata metadata, PathInits inits) {
        this(MrInfo.class, metadata, inits);
    }

    public QMrInfo(Class<? extends MrInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userArtist = inits.isInitialized("userArtist") ? new com.study.palette.module.user.entity.QUserArtist(forProperty("userArtist")) : null;
    }

}

