package com.study.palette.module.albumArt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlbumArtistInfo is a Querydsl query type for AlbumArtistInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlbumArtistInfo extends EntityPathBase<AlbumArtistInfo> {

    private static final long serialVersionUID = -723273130L;

    public static final QAlbumArtistInfo albumArtistInfo = new QAlbumArtistInfo("albumArtistInfo");

    public final ListPath<AlbumArtistFile, QAlbumArtistFile> albumArtistFile = this.<AlbumArtistFile, QAlbumArtistFile>createList("albumArtistFile", AlbumArtistFile.class, QAlbumArtistFile.class, PathInits.DIRECT2);

    public final ListPath<AlbumArtistLicenseInfo, QAlbumArtistLicenseInfo> albumArtistLicenseInfo = this.<AlbumArtistLicenseInfo, QAlbumArtistLicenseInfo>createList("albumArtistLicenseInfo", AlbumArtistLicenseInfo.class, QAlbumArtistLicenseInfo.class, PathInits.DIRECT2);

    public final ListPath<AlbumArtistReview, QAlbumArtistReview> albumArtistReview = this.<AlbumArtistReview, QAlbumArtistReview>createList("albumArtistReview", AlbumArtistReview.class, QAlbumArtistReview.class, PathInits.DIRECT2);

    public final StringPath artistId = createString("artistId");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath editInfo = createString("editInfo");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> salesType = createNumber("salesType", Integer.class);

    public final StringPath serviceExplain = createString("serviceExplain");

    public final StringPath serviceName = createString("serviceName");

    public final BooleanPath serviceStatus = createBoolean("serviceStatus");

    public final StringPath userId = createString("userId");

    public QAlbumArtistInfo(String variable) {
        super(AlbumArtistInfo.class, forVariable(variable));
    }

    public QAlbumArtistInfo(Path<? extends AlbumArtistInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlbumArtistInfo(PathMetadata metadata) {
        super(AlbumArtistInfo.class, metadata);
    }

}

