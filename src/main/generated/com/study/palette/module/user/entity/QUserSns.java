package com.study.palette.module.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserSns is a Querydsl query type for UserSns
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserSns extends EntityPathBase<UserSns> {

    private static final long serialVersionUID = -351530008L;

    public static final QUserSns userSns = new QUserSns("userSns");

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> createAt = createDate("createAt", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final StringPath userId = createString("userId");

    public QUserSns(String variable) {
        super(UserSns.class, forVariable(variable));
    }

    public QUserSns(Path<? extends UserSns> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserSns(PathMetadata metadata) {
        super(UserSns.class, metadata);
    }

}

