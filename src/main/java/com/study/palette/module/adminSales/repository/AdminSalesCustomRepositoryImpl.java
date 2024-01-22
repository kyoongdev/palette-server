package com.study.palette.module.adminSales.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLTemplates;
import com.study.palette.module.adminSales.dto.AdminSalesResponseDto;
import com.study.palette.module.adminSales.service.AdminSalesConditions;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.artist.entity.QArtistInfo;
import com.study.palette.module.mixMastering.entity.QMixMasteringInfo;
import com.study.palette.module.mrBeat.entity.QMrBeatInfo;
import com.study.palette.module.recording.entity.QRecordingInfo;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AdminSalesCustomRepositoryImpl implements AdminSalesCustomRepository {

  private final SQLTemplates sqlTemplates;
  private EntityManager entityManager;

  @Autowired
  public AdminSalesCustomRepositoryImpl(SQLTemplates sqlTemplates, EntityManager entityManager) {
    this.sqlTemplates = sqlTemplates;
    this.entityManager = entityManager;
  }

  @Override
  @Transactional(readOnly = true)
  public List<AdminSalesResponseDto> findAllByServiceStatusAndCreatedAtDesc(AdminSalesConditions query, Pageable pageable) {
    JPASQLQuery<AdminSalesResponseDto> sqlQuery = new JPASQLQuery<>(entityManager, sqlTemplates);
    JPASQLQuery<AdminSalesResponseDto> sqlQueryUnion = new JPASQLQuery<>(entityManager, sqlTemplates);

    QAlbumArtInfo albumArtInfo = new QAlbumArtInfo("AlbumArtInfo");
    QRecordingInfo recordingInfo = new QRecordingInfo("RecordingInfo");
    QArtistInfo artistInfo = new QArtistInfo("ArtistInfo");
    QMrBeatInfo mrBeatInfo = new QMrBeatInfo("MrBeatInfo");
    QMixMasteringInfo mixMasteringInfo = new QMixMasteringInfo("MixMasteringInfo");

    //union all 쿼리
    List<AdminSalesResponseDto> result = sqlQuery
        .select(
            Projections.fields(AdminSalesResponseDto.class,
                Expressions.stringTemplate("allServices.serviceName").as("serviceName"),
                Expressions.numberTemplate(Integer.class, "allServices.serviceType").as("serviceType"),
                // 원인은 모르지만 JPASQLQuery에서 만든 쿼리 리턴값은 TimeStamp 로 리턴되고 있음
                // 밑에처럼 LocalDatTime 으로 명시를 해줘도 TimeStamp 로 리턴되는 매우 꼴받는 상황임 하..
//                Expressions.dateTimeTemplate(LocalDateTime.class, "allServices.createdAt").as("createdAt") TODO 컬럼 추가후 반영
                Expressions.dateTimeTemplate(Timestamp.class, "allServices.createdAt").as("createdAt")
            )
        )
        .from(
            sqlQueryUnion.unionAll(
                SQLExpressions
                    .select(
                        albumArtInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "1").as("serviceType"),
                        albumArtInfo.createdAt.as("createdAt")
//                        albumArtInfo.registerDeadline.as("registerDeadline")
                    )
                    .from(albumArtInfo)
                    .where(albumArtInfo.serviceStatus.eq(query.getIsRegistrationCompleted())),
                SQLExpressions
                    .select(
                        recordingInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "2").as("serviceType"),
                        recordingInfo.createdAt.as("createdAt")
//                        recordingInfo.registerDeadline.as("registerDeadline")
                    )
                    .from(recordingInfo)
                    .where(recordingInfo.serviceStatus.eq(query.getIsRegistrationCompleted())),
                SQLExpressions
                    .select(
                        artistInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "3").as("serviceType"),
                        artistInfo.createdAt.as("createdAt")
//                        artistInfo.registerDeadline.as("registerDeadline")
                    )
                    .from(artistInfo)
                    .where(artistInfo.serviceStatus.eq(query.getIsRegistrationCompleted())),
                SQLExpressions
                    .select(
                        mrBeatInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "5").as("serviceType"),
                        mrBeatInfo.createdAt.as("createdAt")
//                        mrBeatInfo.registerDeadline.as("registerDeadline")
                    )
                    .from(mrBeatInfo)
                    .where(mrBeatInfo.serviceStatus.eq(query.getIsRegistrationCompleted())),
                SQLExpressions
                    .select(
                        mixMasteringInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "4").as("serviceType"),
                        mixMasteringInfo.createdAt.as("createdAt")
//                        mixMasteringInfo.registerDeadline.as("registerDeadline")
                    )
                    .from(mixMasteringInfo)
                    .where(mixMasteringInfo.serviceStatus.eq(query.getIsRegistrationCompleted()))
            ).as("allServices")
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    return result;
  }
}