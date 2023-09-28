package com.study.palette.module.mr.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMrFile is a Querydsl query type for MrFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMrFile extends EntityPathBase<MrFile> {

    private static final long serialVersionUID = 874741440L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMrFile mrFile = new QMrFile("mrFile");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Integer> fileType = createNumber("fileType", Integer.class);

    public final StringPath id = createString("id");

    public final BooleanPath isUse = createBoolean("isUse");

    public final QMrInfo mrInfo;

    public final StringPath originFileName = createString("originFileName");

    public final StringPath suffix = createString("suffix");

    public final StringPath targetId = createString("targetId");

    public final StringPath uploadFileName = createString("uploadFileName");

    public final StringPath uploadFilePath = createString("uploadFilePath");

    public final NumberPath<Integer> uploadFileSize = createNumber("uploadFileSize", Integer.class);

    public final StringPath userId = createString("userId");

    public QMrFile(String variable) {
        this(MrFile.class, forVariable(variable), INITS);
    }

    public QMrFile(Path<? extends MrFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMrFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMrFile(PathMetadata metadata, PathInits inits) {
        this(MrFile.class, metadata, inits);
    }

    public QMrFile(Class<? extends MrFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mrInfo = inits.isInitialized("mrInfo") ? new QMrInfo(forProperty("mrInfo"), inits.get("mrInfo")) : null;
    }

}

