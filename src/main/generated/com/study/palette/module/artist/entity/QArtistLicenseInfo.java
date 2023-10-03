package com.study.palette.module.artist.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtistLicenseInfo is a Querydsl query type for ArtistLicenseInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtistLicenseInfo extends EntityPathBase<ArtistLicenseInfo> {

    private static final long serialVersionUID = 657756807L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArtistLicenseInfo artistLicenseInfo = new QArtistLicenseInfo("artistLicenseInfo");

    public final QArtistInfo artistInfo;

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Integer> draftCount = createNumber("draftCount", Integer.class);

    public final StringPath id = createString("id");

    public final BooleanPath isAssign = createBoolean("isAssign");

    public final BooleanPath isOtherUseApproved = createBoolean("isOtherUseApproved");

    public final BooleanPath isServeOriginFile = createBoolean("isServeOriginFile");

    public final NumberPath<Integer> licenseType = createNumber("licenseType", Integer.class);

    public final DatePath<java.time.LocalDate> period = createDate("period", java.time.LocalDate.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath serveFile = createString("serveFile");

    public final NumberPath<Integer> updateCount = createNumber("updateCount", Integer.class);

    public final StringPath userId = createString("userId");

    public QArtistLicenseInfo(String variable) {
        this(ArtistLicenseInfo.class, forVariable(variable), INITS);
    }

    public QArtistLicenseInfo(Path<? extends ArtistLicenseInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArtistLicenseInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArtistLicenseInfo(PathMetadata metadata, PathInits inits) {
        this(ArtistLicenseInfo.class, metadata, inits);
    }

    public QArtistLicenseInfo(Class<? extends ArtistLicenseInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.artistInfo = inits.isInitialized("artistInfo") ? new QArtistInfo(forProperty("artistInfo"), inits.get("artistInfo")) : null;
    }

}

