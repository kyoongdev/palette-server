package com.study.palette.module.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFile is a Querydsl query type for UserFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFile extends EntityPathBase<UserFile> {

    private static final long serialVersionUID = 1987079436L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFile userFile = new QUserFile("userFile");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final NumberPath<Integer> filterMasterCode = createNumber("filterMasterCode", Integer.class);

    public final StringPath id = createString("id");

    public final BooleanPath isUse = createBoolean("isUse");

    public final StringPath originFileName = createString("originFileName");

    public final StringPath suffix = createString("suffix");

    public final StringPath uploadFileName = createString("uploadFileName");

    public final StringPath uploadFilePath = createString("uploadFilePath");

    public final NumberPath<Integer> uploadFileSize = createNumber("uploadFileSize", Integer.class);

    public final QUser user;

    public QUserFile(String variable) {
        this(UserFile.class, forVariable(variable), INITS);
    }

    public QUserFile(Path<? extends UserFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFile(PathMetadata metadata, PathInits inits) {
        this(UserFile.class, metadata, inits);
    }

    public QUserFile(Class<? extends UserFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}
