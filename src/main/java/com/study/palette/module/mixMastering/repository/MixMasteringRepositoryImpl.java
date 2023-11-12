package com.study.palette.module.mixMastering.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.module.mixMastering.dto.MixMasteringsDto;
import com.study.palette.module.mixMastering.dto.query.FindMixMasteringQuery;
import com.study.palette.module.mixMastering.entity.QMixMasteringFile;
import com.study.palette.module.mixMastering.entity.QMixMasteringInfo;
import com.study.palette.module.mixMastering.entity.QMixMasteringLicenseInfo;
import com.study.palette.module.mixMastering.entity.QMixMasteringRequest;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class MixMasteringRepositoryImpl implements MixMasteringCustomRepository {

  private final JPAQueryFactory queryFactory;

  @Autowired
  public MixMasteringRepositoryImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public Page<MixMasteringsDto> findAll(FindMixMasteringQuery query, Pageable pageable) {
    QMixMasteringInfo mixMasteringInfo = QMixMasteringInfo.mixMasteringInfo;
    QMixMasteringFile mixMasteringFile = QMixMasteringFile.mixMasteringFile;
    QMixMasteringLicenseInfo mixMasteringLicenseInfo = QMixMasteringLicenseInfo.mixMasteringLicenseInfo;
    QUserMusician userMusician = QUserMusician.userMusician;
    QMixMasteringRequest mixMasteringRequest = QMixMasteringRequest.mixMasteringRequest;

    /*
    * 네임
    * 아티스트
    * 장르
    * 작업전파일
    * 작업후파일
    * 가격
    * 썸네일
    * */

    List<MixMasteringsDto> result = queryFactory
        .select(Projections.constructor(MixMasteringsDto.class,
            mixMasteringInfo.id,
            mixMasteringInfo.serviceName,
            userMusician.name.as("artistName"),
            mixMasteringInfo.genre.as("genre"),
            mixMasteringInfo.beforeJobMusic.as("beforeJobMusic"),
            mixMasteringInfo.afterJobMusic.as("afterJobMusic"),
            mixMasteringLicenseInfo.price.as("price"),
            mixMasteringFile.url.as("thumbnailUrl"),
            mixMasteringRequest.id.count().as("requestCount")))
        .from(mixMasteringInfo)
        .leftJoin(mixMasteringRequest)
        .on(mixMasteringInfo.id.eq(mixMasteringRequest.mixMasteringInfo.id))
        .leftJoin(mixMasteringFile)
        .on(mixMasteringInfo.id.eq(mixMasteringFile.mixMasteringInfo.id)
            .and(mixMasteringFile.isThumbnail.isTrue()))
        .leftJoin(mixMasteringLicenseInfo)
        .on(mixMasteringInfo.id.eq(mixMasteringLicenseInfo.mixMasteringInfo.id)
            .and(mixMasteringLicenseInfo.licenseType.eq(10)))
        .leftJoin(userMusician)
        .on(mixMasteringInfo.user.id.eq(userMusician.user.id))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .where(mixMasteringInfo.genre.eq(query.getGenre().getGenre()))
        .groupBy(mixMasteringInfo.id,
            mixMasteringInfo.serviceName,
            userMusician.name,
            mixMasteringInfo.genre,
            mixMasteringInfo.beforeJobMusic,
            mixMasteringInfo.afterJobMusic,
            mixMasteringFile.url,
            mixMasteringLicenseInfo.price)
        .orderBy(query.getSort())
        .fetch();

    return new PageImpl<>(result, pageable, result.size());
  }
}
