package com.study.palette.module.serviceProgress.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QServiceProgressInfo is a Querydsl query type for ServiceProgressInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QServiceProgressInfo extends EntityPathBase<ServiceProgressInfo> {

    private static final long serialVersionUID = -808710578L;

    public static final QServiceProgressInfo serviceProgressInfo = new QServiceProgressInfo("serviceProgressInfo");

    public final StringPath completeComment = createString("completeComment");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> dueDate = createDate("dueDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final BooleanPath isComplete = createBoolean("isComplete");

    public final NumberPath<Integer> licenseType = createNumber("licenseType", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath refundComment = createString("refundComment");

    public final StringPath serviceName = createString("serviceName");

    public final ListPath<ServiceProgressFile, QServiceProgressFile> ServiceProgressFile = this.<ServiceProgressFile, QServiceProgressFile>createList("ServiceProgressFile", ServiceProgressFile.class, QServiceProgressFile.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final BooleanPath status = createBoolean("status");

    public final StringPath userId = createString("userId");

    public final NumberPath<Integer> workProgress = createNumber("workProgress", Integer.class);

    public QServiceProgressInfo(String variable) {
        super(ServiceProgressInfo.class, forVariable(variable));
    }

    public QServiceProgressInfo(Path<? extends ServiceProgressInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QServiceProgressInfo(PathMetadata metadata) {
        super(ServiceProgressInfo.class, metadata);
    }

}

