package com.study.palette.module.albumArt.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.module.albumArt.dto.info.AlbumArtsResponseDto;
import com.study.palette.module.albumArt.entity.QAlbumArtFile;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtLicenseInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtRequest;
import com.study.palette.module.albumArt.service.AlbumArtConditions;
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
    QUsersMusician userMusician = QUsersMusician.usersMusician;
    QUsersFile usersFile = QUsersFile.usersFile;

    List<AlbumArtsResponseDto> result = queryFactory
        .select(Projections.constructor(AlbumArtsResponseDto.class,
            albumArtInfo.id,
            albumArtInfo.serviceName,
            albumArtInfo.salesType,
            userMusician.name.as("musicianName"),
            albumArtFile.url.as("thumbnailUrl"),
            albumArtLicenseInfo.price.as("price"),
            albumArtRequest.id.count().as("requestCount"),
            usersFile.url.as("profileUrl")))
        .from(albumArtInfo)
        .leftJoin(albumArtRequest)
        .on(albumArtInfo.id.eq(albumArtRequest.albumArtInfo.id))
        .leftJoin(albumArtFile)
        .on(albumArtInfo.id.eq(albumArtFile.albumArtInfo.id)
            .and(albumArtFile.isThumbnail.isTrue()))
        .leftJoin(albumArtLicenseInfo)
        .on(albumArtInfo.id.eq(albumArtLicenseInfo.albumArtInfo.id))
        .leftJoin(usersFile)
        .on(albumArtInfo.users.id.eq(usersFile.users.id)
            .and(usersFile.isProfile.isTrue()))
        .leftJoin(userMusician)
        .on(albumArtInfo.users.id.eq(userMusician.users.id))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .where(query.getSaleTypeCondition(albumArtInfo).
            and(albumArtInfo.isSelling.eq(true)))
        .groupBy(albumArtInfo.id,
            albumArtInfo.serviceName,
            albumArtInfo.salesType,
            albumArtFile.url,
            albumArtLicenseInfo.price,
            usersFile.url,
            userMusician.name)
        .orderBy(query.getSort())
        .fetch();

    return new PageImpl<>(result, pageable, result.size());
  }
}
