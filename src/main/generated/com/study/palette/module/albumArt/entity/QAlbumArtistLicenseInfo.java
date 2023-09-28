package com.study.palette.module.albumArt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlbumArtistLicenseInfo is a Querydsl query type for AlbumArtistLicenseInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlbumArtistLicenseInfo extends EntityPathBase<AlbumArtistLicenseInfo> {

    private static final long serialVersionUID = -1468083737L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlbumArtistLicenseInfo albumArtistLicenseInfo = new QAlbumArtistLicenseInfo("albumArtistLicenseInfo");

    public final QAlbumArtistInfo albumArtistInfo;

    public final DatePath<java.time.LocalDate> createAt = createDate("createAt", java.time.LocalDate.class);

    public final NumberPath<Integer> draftCount = createNumber("draftCount", Integer.class);

    public final StringPath id = createString("id");

    public final BooleanPath isAssign = createBoolean("isAssign");

    public final BooleanPath isOtherUseApproved = createBoolean("isOtherUseApproved");

    public final BooleanPath isServeOriginFile = createBoolean("isServeOriginFile");

    public final BooleanPath isUseCommercial = createBoolean("isUseCommercial");

    public final NumberPath<Integer> licenseType = createNumber("licenseType", Integer.class);

    public final DatePath<java.time.LocalDate> period = createDate("period", java.time.LocalDate.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath servedFile = createString("servedFile");

    public final NumberPath<Integer> updateCount = createNumber("updateCount", Integer.class);

    public final StringPath userId = createString("userId");

    public QAlbumArtistLicenseInfo(String variable) {
        this(AlbumArtistLicenseInfo.class, forVariable(variable), INITS);
    }

    public QAlbumArtistLicenseInfo(Path<? extends AlbumArtistLicenseInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlbumArtistLicenseInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlbumArtistLicenseInfo(PathMetadata metadata, PathInits inits) {
        this(AlbumArtistLicenseInfo.class, metadata, inits);
    }

    public QAlbumArtistLicenseInfo(Class<? extends AlbumArtistLicenseInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.albumArtistInfo = inits.isInitialized("albumArtistInfo") ? new QAlbumArtistInfo(forProperty("albumArtistInfo")) : null;
    }

}

