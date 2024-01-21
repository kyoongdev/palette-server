package com.study.palette.module.mixMastering.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.module.mixMastering.dto.MixMasteringsResponseDto;
import com.study.palette.module.mixMastering.entity.QMixMasteringFile;
import com.study.palette.module.mixMastering.entity.QMixMasteringInfo;
import com.study.palette.module.mixMastering.entity.QMixMasteringLicenseInfo;
import com.study.palette.module.mixMastering.entity.QMixMasteringRequest;
import com.study.palette.module.mixMastering.service.MixMasteringConditions;
import com.study.palette.module.musician.entity.QUsersMusician;
import com.study.palette.module.users.entity.QUsersFile;
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
  public Page<MixMasteringsResponseDto> findAll(MixMasteringConditions query, Pageable pageable) {
    QMixMasteringInfo mixMasteringInfo = QMixMasteringInfo.mixMasteringInfo;
    QMixMasteringFile mixMasteringFile = QMixMasteringFile.mixMasteringFile;
    QMixMasteringLicenseInfo mixMasteringLicenseInfo = QMixMasteringLicenseInfo.mixMasteringLicenseInfo;
    QUsersMusician userMusician = QUsersMusician.usersMusician;
    QMixMasteringRequest mixMasteringRequest = QMixMasteringRequest.mixMasteringRequest;

    QUsersFile usersFile = QUsersFile.usersFile;
    /*
     * 네임
     * 아티스트
     * 장르
     * 작업전파일
     * 작업후파일
     * 가격
     * 썸네일
     * */

    List<MixMasteringsResponseDto> result = queryFactory
        .select(Projections.constructor(MixMasteringsResponseDto.class,
            mixMasteringInfo.id,
            mixMasteringInfo.serviceName,
            userMusician.name.as("artistName"),
            mixMasteringInfo.genre.as("genre"),
            mixMasteringInfo.beforeJobMusic.as("beforeJobMusic"),
            mixMasteringInfo.afterJobMusic.as("afterJobMusic"),
            mixMasteringLicenseInfo.price.as("price"),
            mixMasteringFile.url.as("thumbnailUrl"),
            mixMasteringRequest.id.count().as("requestCount"),
            usersFile.uploadFilePath.as("profileUrl")))
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
        .on(mixMasteringInfo.users.id.eq(userMusician.users.id))
        .leftJoin(usersFile)
        .on(mixMasteringInfo.users.id.eq(usersFile.users.id)
            .and(usersFile.isProfile.isTrue()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .where(query.getGenreCondition(mixMasteringInfo))
        .groupBy(mixMasteringInfo.id,
            mixMasteringInfo.serviceName,
            userMusician.name,
            mixMasteringInfo.genre,
            mixMasteringInfo.beforeJobMusic,
            mixMasteringInfo.afterJobMusic,
            mixMasteringFile.url,
            mixMasteringLicenseInfo.price,
            usersFile.uploadFilePath)
        .orderBy(query.getSort())
        .fetch();

    return new PageImpl<>(result, pageable, result.size());
  }
}
