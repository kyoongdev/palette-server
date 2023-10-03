package com.study.palette.module.artist.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtistFile is a Querydsl query type for ArtistFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtistFile extends EntityPathBase<ArtistFile> {

    private static final long serialVersionUID = -1533149564L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArtistFile artistFile = new QArtistFile("artistFile");

    public final QArtistInfo artistInfo;

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Integer> fileType = createNumber("fileType", Integer.class);

    public final StringPath id = createString("id");

    public final BooleanPath isThumbnail = createBoolean("isThumbnail");

    public final BooleanPath isUse = createBoolean("isUse");

    public final StringPath originFileName = createString("originFileName");

    public final StringPath suffix = createString("suffix");

    public final StringPath targetId = createString("targetId");

    public final StringPath uploadFileName = createString("uploadFileName");

    public final StringPath uploadFilePath = createString("uploadFilePath");

    public final NumberPath<Integer> uploadFileSize = createNumber("uploadFileSize", Integer.class);

    public final StringPath userId = createString("userId");

    public QArtistFile(String variable) {
        this(ArtistFile.class, forVariable(variable), INITS);
    }

    public QArtistFile(Path<? extends ArtistFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArtistFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArtistFile(PathMetadata metadata, PathInits inits) {
        this(ArtistFile.class, metadata, inits);
    }

    public QArtistFile(Class<? extends ArtistFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.artistInfo = inits.isInitialized("artistInfo") ? new QArtistInfo(forProperty("artistInfo"), inits.get("artistInfo")) : null;
    }

}

