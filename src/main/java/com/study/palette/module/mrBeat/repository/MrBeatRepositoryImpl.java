package com.study.palette.module.mrBeat.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.module.mrBeat.dto.FindMrBeatsQuery;
import com.study.palette.module.mrBeat.dto.MrBeatResponseDto;
import com.study.palette.module.mrBeat.entity.QMrBeatFile;
import com.study.palette.module.mrBeat.entity.QMrBeatInfo;
import com.study.palette.module.mrBeat.entity.QMrBeatLicenseInfo;
import com.study.palette.module.mrBeat.entity.QMrBeatMusicFile;
import com.study.palette.module.mrBeat.entity.QMrBeatRequest;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class MrBeatRepositoryImpl implements MrBeatCustomRepository {

  private final JPAQueryFactory queryFactory;

  @Autowired
  public MrBeatRepositoryImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public Page<MrBeatResponseDto> findAll(FindMrBeatsQuery query, Pageable pageable) {

    QMrBeatInfo mrBeatInfo = QMrBeatInfo.mrBeatInfo;
    QMrBeatFile mrBeatFile = QMrBeatFile.mrBeatFile;
    QMrBeatMusicFile mrBeatMusicFile = QMrBeatMusicFile.mrBeatMusicFile;
    QMrBeatLicenseInfo mrBeatLicenseInfo = QMrBeatLicenseInfo.mrBeatLicenseInfo;
    QMrBeatRequest mrBeatRequest = QMrBeatRequest.mrBeatRequest;

    List<MrBeatResponseDto> result = queryFactory
        .select(Projections.constructor(MrBeatResponseDto.class,
            mrBeatInfo.id,
            mrBeatInfo.serviceName,
            mrBeatInfo.mood,
            mrBeatInfo.genre,
            mrBeatInfo.salesType,
            mrBeatFile.url.as("fileUrl"),
            mrBeatMusicFile.url.as("musicFileUrl"),
            mrBeatLicenseInfo.price.as("price"),
            mrBeatRequest.id.count().as("requestCount"),
            mrBeatInfo.users.name,
            mrBeatInfo.users.profileImage
            ))
        .from(mrBeatInfo)
        .leftJoin(mrBeatRequest)
        .on(mrBeatInfo.id.eq(mrBeatRequest.mrBeatInfo.id))
        .leftJoin(mrBeatFile)
        .on(mrBeatInfo.id.eq(mrBeatFile.mrBeatInfo.id))
        .leftJoin(mrBeatMusicFile)
        .on(mrBeatInfo.id.eq(mrBeatMusicFile.mrBeatInfo.id))
        .leftJoin(mrBeatLicenseInfo)
        .on(mrBeatInfo.id.eq(mrBeatLicenseInfo.mrBeatInfo.id))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .where(query.getSaleTypeCondition(mrBeatInfo)
            .and(query.getGenreTypeCondition(mrBeatInfo))
            .and(query.getMoodTypeCondition(mrBeatInfo))
            .and(mrBeatInfo.isSelling.isTrue()))
        .groupBy(mrBeatInfo.id,
            mrBeatInfo.serviceName,
            mrBeatInfo.salesType,
            mrBeatInfo.users.name,
            mrBeatFile.url,
            mrBeatMusicFile.url,
            mrBeatLicenseInfo.price,
            mrBeatInfo.users.name,
            mrBeatInfo.users.profileImage)
        .orderBy(query.getSort())
        .fetch();

    return new PageImpl<>(result, pageable, result.size());
  }
}
