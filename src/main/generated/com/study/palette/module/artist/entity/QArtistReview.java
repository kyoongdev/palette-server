package com.study.palette.module.artist.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtistReview is a Querydsl query type for ArtistReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtistReview extends EntityPathBase<ArtistReview> {

    private static final long serialVersionUID = 157212256L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArtistReview artistReview = new QArtistReview("artistReview");

    public final QArtistInfo artistInfo;

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final StringPath review = createString("review");

    public final StringPath userId = createString("userId");

    public QArtistReview(String variable) {
        this(ArtistReview.class, forVariable(variable), INITS);
    }

    public QArtistReview(Path<? extends ArtistReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArtistReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArtistReview(PathMetadata metadata, PathInits inits) {
        this(ArtistReview.class, metadata, inits);
    }

    public QArtistReview(Class<? extends ArtistReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.artistInfo = inits.isInitialized("artistInfo") ? new QArtistInfo(forProperty("artistInfo")) : null;
    }

}

