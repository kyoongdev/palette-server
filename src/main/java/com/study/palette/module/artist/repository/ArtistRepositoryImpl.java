package com.study.palette.module.artist.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.dto.query.FindArtistsQuery;
import com.study.palette.module.artist.entity.QArtistFile;
import com.study.palette.module.artist.entity.QArtistInfo;
import com.study.palette.module.artist.entity.QArtistLicenseInfo;
import com.study.palette.module.artist.entity.QArtistRequest;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistRepositoryImpl implements ArtistCustomRepository {

  private final JPAQueryFactory queryFactory;

  @Autowired
  public ArtistRepositoryImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public Page<ArtistResponseDto> findAll(FindArtistsQuery query, Pageable pageable) {
    QArtistInfo artistInfo = QArtistInfo.artistInfo;
    QArtistFile artistFile = QArtistFile.artistFile;
    QArtistLicenseInfo artistLicenseInfo = QArtistLicenseInfo.artistLicenseInfo;
    QArtistRequest artistRequest = QArtistRequest.artistRequest;

    List<ArtistResponseDto> result = queryFactory
        .select(Projections.constructor(ArtistResponseDto.class,
            artistInfo.id,
            artistInfo.serviceName,
            artistInfo.salesType,
            artistInfo.users.name.as("userName"),
            artistFile.uploadFilePath.as("fileUrl"),
            artistLicenseInfo.price.as("price"),
            artistRequest.id.count().as("requestCount")))
        .from(artistInfo)
        .leftJoin(artistRequest)
        .on(artistInfo.id.eq(artistRequest.artistInfo.id))
        .leftJoin(artistFile)
        .on(artistInfo.id.eq(artistFile.artistInfo.id)
            .and(artistFile.isThumbnail.isTrue()))
        .leftJoin(artistLicenseInfo)
        .on(artistInfo.id.eq(artistLicenseInfo.artistInfo.id)
            .and(artistLicenseInfo.licenseType.eq(10)))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .where(artistInfo.salesType.eq(query.getSaleType()))
        .groupBy(artistInfo.id,
            artistInfo.serviceName,
            artistInfo.salesType,
            artistInfo.users.name,
            artistFile.uploadFilePath,
            artistLicenseInfo.price)
        .orderBy(query.getSort())
        .fetch();

    return new PageImpl<>(result, pageable, result.size());

  }

}