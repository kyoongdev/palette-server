package com.study.palette.module.artist.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtistInfo is a Querydsl query type for ArtistInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtistInfo extends EntityPathBase<ArtistInfo> {

    private static final long serialVersionUID = -1533055562L;

    public static final QArtistInfo artistInfo = new QArtistInfo("artistInfo");

    public final ListPath<ArtistFile, QArtistFile> artistFile = this.<ArtistFile, QArtistFile>createList("artistFile", ArtistFile.class, QArtistFile.class, PathInits.DIRECT2);

    public final ListPath<ArtistLicenseInfo, QArtistLicenseInfo> artistLicenseInfo = this.<ArtistLicenseInfo, QArtistLicenseInfo>createList("artistLicenseInfo", ArtistLicenseInfo.class, QArtistLicenseInfo.class, PathInits.DIRECT2);

    public final ListPath<ArtistReview, QArtistReview> artistReview = this.<ArtistReview, QArtistReview>createList("artistReview", ArtistReview.class, QArtistReview.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> createAt = createDate("createAt", java.time.LocalDate.class);

    public final StringPath editInfo = createString("editInfo");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> salesType = createNumber("salesType", Integer.class);

    public final StringPath serviceInfo = createString("serviceInfo");

    public final StringPath serviceName = createString("serviceName");

    public final NumberPath<Integer> serviceStatus = createNumber("serviceStatus", Integer.class);

    public final StringPath userId = createString("userId");

    public QArtistInfo(String variable) {
        super(ArtistInfo.class, forVariable(variable));
    }

    public QArtistInfo(Path<? extends ArtistInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArtistInfo(PathMetadata metadata) {
        super(ArtistInfo.class, metadata);
    }

}

