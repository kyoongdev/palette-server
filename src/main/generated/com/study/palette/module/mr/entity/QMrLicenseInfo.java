package com.study.palette.module.mr.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMrLicenseInfo is a Querydsl query type for MrLicenseInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMrLicenseInfo extends EntityPathBase<MrLicenseInfo> {

    private static final long serialVersionUID = -146434357L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMrLicenseInfo mrLicenseInfo = new QMrLicenseInfo("mrLicenseInfo");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final BooleanPath isArrangement = createBoolean("isArrangement");

    public final BooleanPath isBackgroundPlay = createBoolean("isBackgroundPlay");

    public final BooleanPath isMakeMusicVideo = createBoolean("isMakeMusicVideo");

    public final BooleanPath isMakeNewSong = createBoolean("isMakeNewSong");

    public final BooleanPath isSellShare = createBoolean("isSellShare");

    public final BooleanPath isUseCommercial = createBoolean("isUseCommercial");

    public final NumberPath<Integer> licenseType = createNumber("licenseType", Integer.class);

    public final QMrInfo mrInfo;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath servedFile = createString("servedFile");

    public QMrLicenseInfo(String variable) {
        this(MrLicenseInfo.class, forVariable(variable), INITS);
    }

    public QMrLicenseInfo(Path<? extends MrLicenseInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMrLicenseInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMrLicenseInfo(PathMetadata metadata, PathInits inits) {
        this(MrLicenseInfo.class, metadata, inits);
    }

    public QMrLicenseInfo(Class<? extends MrLicenseInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mrInfo = inits.isInitialized("mrInfo") ? new QMrInfo(forProperty("mrInfo"), inits.get("mrInfo")) : null;
    }

}

