package com.study.palette.module.albumArt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlbumArtistReview is a Querydsl query type for AlbumArtistReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlbumArtistReview extends EntityPathBase<AlbumArtistReview> {

    private static final long serialVersionUID = 969048832L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlbumArtistReview albumArtistReview = new QAlbumArtistReview("albumArtistReview");

    public final QAlbumArtistInfo albumArtistInfo;

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final StringPath review = createString("review");

    public final StringPath userId = createString("userId");

    public QAlbumArtistReview(String variable) {
        this(AlbumArtistReview.class, forVariable(variable), INITS);
    }

    public QAlbumArtistReview(Path<? extends AlbumArtistReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlbumArtistReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlbumArtistReview(PathMetadata metadata, PathInits inits) {
        this(AlbumArtistReview.class, metadata, inits);
    }

    public QAlbumArtistReview(Class<? extends AlbumArtistReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.albumArtistInfo = inits.isInitialized("albumArtistInfo") ? new QAlbumArtistInfo(forProperty("albumArtistInfo")) : null;
    }

}

