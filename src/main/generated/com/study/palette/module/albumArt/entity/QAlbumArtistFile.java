package com.study.palette.module.albumArt.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlbumArtistFile is a Querydsl query type for AlbumArtistFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlbumArtistFile extends EntityPathBase<AlbumArtistFile> {

    private static final long serialVersionUID = -723367132L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlbumArtistFile albumArtistFile = new QAlbumArtistFile("albumArtistFile");

    public final QAlbumArtistInfo albumArtistInfo;

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Integer> fileMasterCode = createNumber("fileMasterCode", Integer.class);

    public final StringPath id = createString("id");

    public final BooleanPath isUse = createBoolean("isUse");

    public final StringPath originFileName = createString("originFileName");

    public final StringPath suffix = createString("suffix");

    public final StringPath targetId = createString("targetId");

    public final StringPath uploadFileName = createString("uploadFileName");

    public final NumberPath<Integer> uploadFileSize = createNumber("uploadFileSize", Integer.class);

    public final StringPath upoladFilePath = createString("upoladFilePath");

    public final StringPath userId = createString("userId");

    public QAlbumArtistFile(String variable) {
        this(AlbumArtistFile.class, forVariable(variable), INITS);
    }

    public QAlbumArtistFile(Path<? extends AlbumArtistFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlbumArtistFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlbumArtistFile(PathMetadata metadata, PathInits inits) {
        this(AlbumArtistFile.class, metadata, inits);
    }

    public QAlbumArtistFile(Class<? extends AlbumArtistFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.albumArtistInfo = inits.isInitialized("albumArtistInfo") ? new QAlbumArtistInfo(forProperty("albumArtistInfo")) : null;
    }

}

