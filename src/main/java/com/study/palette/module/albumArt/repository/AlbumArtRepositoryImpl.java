package com.study.palette.module.albumArt.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.module.albumArt.dto.info.AlbumArtResponseDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.albumArt.entity.QAlbumArtFile;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtLicenseInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtRequest;
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
  public Page<AlbumArtResponseDto> findAll(FindAlbumArtQuery query, Pageable pageable) {
    QAlbumArtInfo albumArtInfo = QAlbumArtInfo.albumArtInfo;
    QAlbumArtFile albumArtFile = QAlbumArtFile.albumArtFile;
    QAlbumArtLicenseInfo albumArtLicenseInfo = QAlbumArtLicenseInfo.albumArtLicenseInfo;
    QAlbumArtRequest albumArtRequest = QAlbumArtRequest.albumArtRequest;

    List<AlbumArtResponseDto> result = queryFactory
        .select(Projections.constructor(AlbumArtResponseDto.class,
            albumArtInfo.id,
            albumArtInfo.serviceName,
            albumArtInfo.salesType,
            albumArtInfo.user.name.as("userName"),
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
        .on(albumArtInfo.id.eq(albumArtLicenseInfo.albumArtInfo.id)
            .and(albumArtLicenseInfo.licenseType.eq(10)))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
//        .where(albumArtInfo.salesType.eq(query.getSaleType()))
        .groupBy(albumArtInfo.id,
            albumArtInfo.serviceName,
            albumArtInfo.salesType,
            albumArtInfo.user.name,
            albumArtFile.upoladFilePath,
            albumArtLicenseInfo.price)
        .orderBy(query.getSort())
        .fetch();

    return new PageImpl<>(result, pageable, result.size());
  }
}
