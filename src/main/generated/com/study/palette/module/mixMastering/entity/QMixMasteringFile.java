package com.study.palette.module.mixMastering.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMixMasteringFile is a Querydsl query type for MixMasteringFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMixMasteringFile extends EntityPathBase<MixMasteringFile> {

    private static final long serialVersionUID = 750520958L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMixMasteringFile mixMasteringFile = new QMixMasteringFile("mixMasteringFile");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Integer> fileType = createNumber("fileType", Integer.class);

    public final StringPath id = createString("id");

    public final BooleanPath isUse = createBoolean("isUse");

    public final QMixMasteringInfo mixMasteringInfo;

    public final StringPath originFileName = createString("originFileName");

    public final StringPath suffix = createString("suffix");

    public final StringPath targetId = createString("targetId");

    public final StringPath uploadFileName = createString("uploadFileName");

    public final StringPath uploadFilePath = createString("uploadFilePath");

    public final NumberPath<Integer> uploadFileSize = createNumber("uploadFileSize", Integer.class);

    public final StringPath userId = createString("userId");

    public QMixMasteringFile(String variable) {
        this(MixMasteringFile.class, forVariable(variable), INITS);
    }

    public QMixMasteringFile(Path<? extends MixMasteringFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMixMasteringFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMixMasteringFile(PathMetadata metadata, PathInits inits) {
        this(MixMasteringFile.class, metadata, inits);
    }

    public QMixMasteringFile(Class<? extends MixMasteringFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mixMasteringInfo = inits.isInitialized("mixMasteringInfo") ? new QMixMasteringInfo(forProperty("mixMasteringInfo"), inits.get("mixMasteringInfo")) : null;
    }

}

