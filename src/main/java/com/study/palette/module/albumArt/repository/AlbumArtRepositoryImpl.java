package com.study.palette.module.albumArt.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.module.albumArt.dto.info.AlbumArtsResponseDto;
import com.study.palette.module.albumArt.entity.QAlbumArtFile;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtLicenseInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtRequest;
import com.study.palette.module.albumArt.service.AlbumArtConditions;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumArtRepositoryImpl implements AlbumArtCustomRepository {

  private final JPAQueryFactory queryFactory;

  @Autowired
  public AlbumArtRepositoryImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public Page<AlbumArtsResponseDto> findAll(AlbumArtConditions query, Pageable pageable) {
    QAlbumArtInfo albumArtInfo = QAlbumArtInfo.albumArtInfo;
    QAlbumArtFile albumArtFile = QAlbumArtFile.albumArtFile;
    QAlbumArtLicenseInfo albumArtLicenseInfo = QAlbumArtLicenseInfo.albumArtLicenseInfo;
    QAlbumArtRequest albumArtRequest = QAlbumArtRequest.albumArtRequest;

    List<AlbumArtsResponseDto> result = queryFactory
        .select(Projections.constructor(AlbumArtsResponseDto.class,
            albumArtInfo.id,
            albumArtInfo.serviceName,
            albumArtInfo.salesType,
            albumArtInfo.users.name.as("userName"),
            albumArtFile.upoladFilePath.as("fileUrl"),
            albumArtLicenseInfo.price.as("price"),
            albumArtRequest.id.count().as("requestCount")))
        .from(albumArtInfo)
        .leftJoin(albumArtRequest)
        .on(albumArtInfo.id.eq(albumArtRequest.albumArtInfo.id))
        .leftJoin(albumArtFile)
        .on(albumArtInfo.id.eq(albumArtFile.albumArtInfo.id)
            .and(albumArtFile.isThumbnail.isTrue()))
        .leftJoin(albumArtLicenseInfo)
        .on(albumArtInfo.id.eq(albumArtLicenseInfo.albumArtInfo.id))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .where(query.getSaleTypeCondition(albumArtInfo))
        .groupBy(albumArtInfo.id,
            albumArtInfo.serviceName,
            albumArtInfo.salesType,
            albumArtInfo.users.name,
            albumArtFile.upoladFilePath,
            albumArtLicenseInfo.price)
        .orderBy(query.getSort())
        .fetch();

    return new PageImpl<>(result, pageable, result.size());
  }
}
