package com.study.palette.module.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFollowing is a Querydsl query type for UserFollowing
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFollowing extends EntityPathBase<UserFollowing> {

    private static final long serialVersionUID = -1512917759L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFollowing userFollowing = new QUserFollowing("userFollowing");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final StringPath followingId = createString("followingId");

    public final StringPath id = createString("id");

    public final QUser user;

    public QUserFollowing(String variable) {
        this(UserFollowing.class, forVariable(variable), INITS);
    }

    public QUserFollowing(Path<? extends UserFollowing> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFollowing(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFollowing(PathMetadata metadata, PathInits inits) {
        this(UserFollowing.class, metadata, inits);
    }

    public QUserFollowing(Class<? extends UserFollowing> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

