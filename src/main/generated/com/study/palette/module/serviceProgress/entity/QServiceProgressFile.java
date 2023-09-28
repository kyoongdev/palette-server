package com.study.palette.module.serviceProgress.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QServiceProgressFile is a Querydsl query type for ServiceProgressFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QServiceProgressFile extends EntityPathBase<ServiceProgressFile> {

    private static final long serialVersionUID = -808804580L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QServiceProgressFile serviceProgressFile = new QServiceProgressFile("serviceProgressFile");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Integer> FilterMasterCode = createNumber("FilterMasterCode", Integer.class);

    public final StringPath id = createString("id");

    public final BooleanPath isUse = createBoolean("isUse");

    public final StringPath originFileName = createString("originFileName");

    public final QServiceProgressInfo serviceProgressInfo;

    public final StringPath suffix = createString("suffix");

    public final StringPath uploadFileName = createString("uploadFileName");

    public final StringPath uploadFilePath = createString("uploadFilePath");

    public final NumberPath<Integer> uploadFileSize = createNumber("uploadFileSize", Integer.class);

    public final StringPath userId = createString("userId");

    public QServiceProgressFile(String variable) {
        this(ServiceProgressFile.class, forVariable(variable), INITS);
    }

    public QServiceProgressFile(Path<? extends ServiceProgressFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QServiceProgressFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QServiceProgressFile(PathMetadata metadata, PathInits inits) {
        this(ServiceProgressFile.class, metadata, inits);
    }

    public QServiceProgressFile(Class<? extends ServiceProgressFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.serviceProgressInfo = inits.isInitialized("serviceProgressInfo") ? new QServiceProgressInfo(forProperty("serviceProgressInfo")) : null;
    }

}

