package com.study.palette.test.jpa.crud.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.test.jpa.crud.model.MixMasteringInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.study.palette.test.jpa.crud.model.QMixMasteringInfo.mixMasteringInfo;

@Repository
@RequiredArgsConstructor
public class MixMasteringRepositoryImpl implements MixMasteringRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public MixMasteringInfo findGenre(String id) {
        return queryFactory
                .selectFrom(mixMasteringInfo)
                .where(mixMasteringInfo.id.eq(id))
                .fetchFirst();
    }


}
