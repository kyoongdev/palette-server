package com.study.palette.module.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFollower is a Querydsl query type for UserFollower
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFollower extends EntityPathBase<UserFollower> {

    private static final long serialVersionUID = -48803922L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFollower userFollower = new QUserFollower("userFollower");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath followerId = createString("followerId");

    public final StringPath id = createString("id");

    public final QUser user;

    public QUserFollower(String variable) {
        this(UserFollower.class, forVariable(variable), INITS);
    }

    public QUserFollower(Path<? extends UserFollower> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFollower(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFollower(PathMetadata metadata, PathInits inits) {
        this(UserFollower.class, metadata, inits);
    }

    public QUserFollower(Class<? extends UserFollower> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

