package com.study.palette.module.mixMastering.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMixMasteringLicenseInfo is a Querydsl query type for MixMasteringLicenseInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMixMasteringLicenseInfo extends EntityPathBase<MixMasteringLicenseInfo> {

    private static final long serialVersionUID = 2750797L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMixMasteringLicenseInfo mixMasteringLicenseInfo = new QMixMasteringLicenseInfo("mixMasteringLicenseInfo");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Integer> draftCount = createNumber("draftCount", Integer.class);

    public final StringPath id = createString("id");

    public final BooleanPath isAssign = createBoolean("isAssign");

    public final BooleanPath isOtherUseApproved = createBoolean("isOtherUseApproved");

    public final BooleanPath isServeOriginFile = createBoolean("isServeOriginFile");

    public final BooleanPath isUseCommercial = createBoolean("isUseCommercial");

    public final NumberPath<Integer> licenseType = createNumber("licenseType", Integer.class);

    public final QMixMasteringInfo mixMasteringInfo;

    public final DatePath<java.time.LocalDate> period = createDate("period", java.time.LocalDate.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath servedType = createString("servedType");

    public final NumberPath<Integer> updateCount = createNumber("updateCount", Integer.class);

    public final StringPath userId = createString("userId");

    public QMixMasteringLicenseInfo(String variable) {
        this(MixMasteringLicenseInfo.class, forVariable(variable), INITS);
    }

    public QMixMasteringLicenseInfo(Path<? extends MixMasteringLicenseInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMixMasteringLicenseInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMixMasteringLicenseInfo(PathMetadata metadata, PathInits inits) {
        this(MixMasteringLicenseInfo.class, metadata, inits);
    }

    public QMixMasteringLicenseInfo(Class<? extends MixMasteringLicenseInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mixMasteringInfo = inits.isInitialized("mixMasteringInfo") ? new QMixMasteringInfo(forProperty("mixMasteringInfo"), inits.get("mixMasteringInfo")) : null;
    }

}

