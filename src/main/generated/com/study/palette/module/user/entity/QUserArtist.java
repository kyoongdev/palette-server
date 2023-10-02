package com.study.palette.module.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserArtist is a Querydsl query type for UserArtist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserArtist extends EntityPathBase<UserArtist> {

    private static final long serialVersionUID = -1811696937L;

    public static final QUserArtist userArtist = new QUserArtist("userArtist");

    public final DatePath<java.time.LocalDate> cratedAt = createDate("cratedAt", java.time.LocalDate.class);

    public final NumberPath<Integer> groupType = createNumber("groupType", Integer.class);

    public final StringPath id = createString("id");

    public final StringPath introduction = createString("introduction");

    public final BooleanPath isAuthorized = createBoolean("isAuthorized");

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final NumberPath<Integer> positionType = createNumber("positionType", Integer.class);

    public final StringPath userId = createString("userId");

    public QUserArtist(String variable) {
        super(UserArtist.class, forVariable(variable));
    }

    public QUserArtist(Path<? extends UserArtist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserArtist(PathMetadata metadata) {
        super(UserArtist.class, metadata);
    }

}

