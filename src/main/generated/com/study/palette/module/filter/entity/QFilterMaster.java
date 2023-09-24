package com.study.palette.module.filter.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFilterMaster is a Querydsl query type for FilterMaster
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFilterMaster extends EntityPathBase<FilterMaster> {

    private static final long serialVersionUID = -827179380L;

    public static final QFilterMaster filterMaster = new QFilterMaster("filterMaster");

    public final StringPath codeName = createString("codeName");

    public final DatePath<java.time.LocalDate> createdAt = createDate("createdAt", java.time.LocalDate.class);

    public final BooleanPath isUse = createBoolean("isUse");

    public final NumberPath<Integer> key = createNumber("key", Integer.class);

    public final StringPath userId = createString("userId");

    public QFilterMaster(String variable) {
        super(FilterMaster.class, forVariable(variable));
    }

    public QFilterMaster(Path<? extends FilterMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFilterMaster(PathMetadata metadata) {
        super(FilterMaster.class, metadata);
    }

}

