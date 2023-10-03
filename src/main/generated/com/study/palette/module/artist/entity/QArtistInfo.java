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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArtistInfo artistInfo = new QArtistInfo("artistInfo");

    public final ListPath<ArtistFile, QArtistFile> artistFile = this.<ArtistFile, QArtistFile>createList("artistFile", ArtistFile.class, QArtistFile.class, PathInits.DIRECT2);

    public final ListPath<ArtistLicenseInfo, QArtistLicenseInfo> artistLicenseInfo = this.<ArtistLicenseInfo, QArtistLicenseInfo>createList("artistLicenseInfo", ArtistLicenseInfo.class, QArtistLicenseInfo.class, PathInits.DIRECT2);

    public final ListPath<ArtistReview, QArtistReview> artistReview = this.<ArtistReview, QArtistReview>createList("artistReview", ArtistReview.class, QArtistReview.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> createAt = createDate("createAt", java.time.LocalDate.class);

    public final StringPath editInfo = createString("editInfo");

    public final com.study.palette.module.filter.entity.QFilterInfo filterInfo;

    public final StringPath id = createString("id");

    public final StringPath serviceInfo = createString("serviceInfo");

    public final StringPath serviceName = createString("serviceName");

    public final NumberPath<Integer> serviceStatus = createNumber("serviceStatus", Integer.class);

    public final com.study.palette.module.user.entity.QUser user;

    public QArtistInfo(String variable) {
        this(ArtistInfo.class, forVariable(variable), INITS);
    }

    public QArtistInfo(Path<? extends ArtistInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArtistInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArtistInfo(PathMetadata metadata, PathInits inits) {
        this(ArtistInfo.class, metadata, inits);
    }

    public QArtistInfo(Class<? extends ArtistInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.filterInfo = inits.isInitialized("filterInfo") ? new com.study.palette.module.filter.entity.QFilterInfo(forProperty("filterInfo"), inits.get("filterInfo")) : null;
        this.user = inits.isInitialized("user") ? new com.study.palette.module.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

