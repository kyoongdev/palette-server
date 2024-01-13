package com.study.palette.module.adminSales.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.study.palette.module.adminSales.dto.AdminSalesResponseDto;
import com.study.palette.module.adminSales.service.AdminSalesConditions;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class AdminSalesCustomRepositoryImpl implements AdminSalesCustomRepository {
  private final SQLQueryFactory queryFactory;
  private final SQLTemplates templates = MySQLTemplates.builder().build();

  @Autowired
  public AdminSalesCustomRepositoryImpl(SQLQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }

  @Override
  public List<AdminSalesResponseDto> findAllByServiceStatusAndCreatedAtDesc(AdminSalesConditions query, Pageable pageable) {

//    List<AdminSalesResponseDto> result = new ArrayList<>();
//    boolean isRegistrationCompleted = query.isRegistrationCompleted();
//    QArtistInfo artistInfo = QArtistInfo.artistInfo;
//    QMixMasteringInfo mixMasteringInfo = QMixMasteringInfo.mixMasteringInfo;
//    QRecordingInfo recordingInfo = QRecordingInfo.recordingInfo;
//    QRecordingInfo mrBeatInfo = QRecordingInfo.recordingInfo;

    // 1-1. 앨범아트
//    result.addAll(queryFactory
//        .select(Projections.constructor(AdminSalesResponseDto.class,
//                albumArtInfo.serviceName,
//                Expressions.stringTemplate("1 as serviceType"),
//                albumArtInfo.createdAt
//            )
//        )
//        .from(albumArtInfo)
//        .where(albumArtInfo.serviceStatus.eq(isRegistrationCompleted))
//        .offset(pageable.getOffset())
//        .limit(pageable.getPageSize())
//        .orderBy(albumArtInfo.createdAt.desc())
//        .fetch());

    // 1-2. 아티스트
//    result.addAll(queryFactory
//        .select(Projections.constructor(AdminSalesResponseDto.class,
//            artistInfo.serviceName,
//            Expressions.stringTemplate("2 as serviceType"),
//            artistInfo.createdAt,
//            //TODO 추후 공휴일까지 포함하여 처리
//            addBusinessDays(artistInfo.createdAt)))
//        .from(artistInfo)
//        .where(artistInfo.serviceStatus.eq(query.isRegistrationCompleted()))
//        .offset(pageable.getOffset())
//        .limit(pageable.getPageSize())
//        .orderBy(artistInfo.createdAt.desc())
//        .fetch());

    // 1-3. 믹스마스터링
//    result.addAll(queryFactory
//        .select(Projections.constructor(AdminSalesResponseDto.class,
//            mixMasteringInfo.serviceName,
//            Expressions.stringTemplate("3 as serviceType"),
//            mixMasteringInfo.createdAt,
//            //TODO 추후 공휴일까지 포함하여 처리
//            addBusinessDays(mixMasteringInfo.createdAt)))
//        .from(mixMasteringInfo)
//        .where(mixMasteringInfo.serviceStatus.eq(query.isRegistrationCompleted()))
//        .offset(pageable.getOffset())
//        .limit(pageable.getPageSize())
//        .orderBy(mixMasteringInfo.createdAt.desc())
//        .fetch());

    // 1-4. 녹음
//    result.addAll(queryFactory
//        .select(Projections.constructor(AdminSalesResponseDto.class,
//            recordingInfo.serviceName,
//            Expressions.stringTemplate("4 as serviceType"),
//            recordingInfo.createdAt.coalesce(LocalDateTime.now()),
//            //TODO 추후 공휴일까지 포함하여 처리
//            addBusinessDays(recordingInfo.createdAt)))
//        .from(recordingInfo)
//        .where(recordingInfo.serviceStatus.eq(query.isRegistrationCompleted()))
//        .offset(pageable.getOffset())
//        .limit(pageable.getPageSize())
//        .orderBy(recordingInfo.createdAt.desc())
//        .fetch());

    // 1-5. MR비트
//    result.addAll(queryFactory
//        .select(Projections.constructor(AdminSalesResponseDto.class,
//            mrBeatInfo.serviceName,
//            Expressions.stringTemplate("5 as serviceType,"),
//            mrBeatInfo.createdAt,
//            //TODO 추후 공휴일까지 포함하여 처리
//            addBusinessDays(mrBeatInfo.createdAt)))
//        .from(mrBeatInfo)
//        .where(mrBeatInfo.serviceStatus.eq(query.isRegistrationCompleted()))
//        .offset(pageable.getOffset())
//        .limit(pageable.getPageSize())
//        .orderBy(mrBeatInfo.createdAt.desc())
//        .fetch());

    // 2. 조회결과를 날짜순으로 정렬후 10개 빼고 삭제
//    result.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
//    if (result.size() > 10) {
//      result.subList(10, result.size()).clear();
//    }
//
//    return result;
  }
}