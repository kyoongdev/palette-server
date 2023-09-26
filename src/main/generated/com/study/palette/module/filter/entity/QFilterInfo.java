package com.study.palette.module.filter.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFilterInfo is a Querydsl query type for FilterInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFilterInfo extends EntityPathBase<FilterInfo> {

    private static final long serialVersionUID = -2097054888L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFilterInfo filterInfo = new QFilterInfo("filterInfo");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final StringPath codeName = createString("codeName");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final BooleanPath isUse = createBoolean("isUse");

    public final QFilterMaster key;

    public final StringPath userId = createString("userId");

    public QFilterInfo(String variable) {
        this(FilterInfo.class, forVariable(variable), INITS);
    }

    public QFilterInfo(Path<? extends FilterInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFilterInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFilterInfo(PathMetadata metadata, PathInits inits) {
        this(FilterInfo.class, metadata, inits);
    }

    public QFilterInfo(Class<? extends FilterInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.key = inits.isInitialized("key") ? new QFilterMaster(forProperty("key")) : null;
    }

}

