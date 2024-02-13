package com.study.palette.module.recording.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.module.musician.entity.QUsersMusician;
import com.study.palette.module.recording.dto.info.RecordingResponseDto;
import com.study.palette.module.recording.entity.QRecordingFile;
import com.study.palette.module.recording.entity.QRecordingInfo;
import com.study.palette.module.recording.entity.QRecordingLicenseInfo;
import com.study.palette.module.recording.service.RecordingConditions;
import com.study.palette.module.users.entity.QUsersFile;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class RecordingRepositoryImpl implements RecordingCustomRepository {

  private final JPAQueryFactory queryFactory;

  @Autowired
  public RecordingRepositoryImpl(EntityManager entityManager) {
    this.queryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public Page<RecordingResponseDto> findAll(RecordingConditions query, Pageable pageable) {
    QRecordingInfo recordingInfo = QRecordingInfo.recordingInfo;
    QRecordingFile recordingFile = QRecordingFile.recordingFile;
    QRecordingLicenseInfo recordingLicenseInfo = QRecordingLicenseInfo.recordingLicenseInfo;
    QUsersFile usersFile = QUsersFile.usersFile;
    QUsersMusician userMusician = QUsersMusician.usersMusician;

    List<RecordingResponseDto> result = queryFactory
        .select(Projections.constructor(RecordingResponseDto.class,
            recordingInfo.id,
            recordingInfo.serviceName,
            recordingInfo.isRecordingEngineering,
            userMusician.name.as("musicianName"),
            recordingFile.url.as("thumbnailUrl"),
            recordingLicenseInfo.price.as("price"),
            usersFile.url.as("profileUrl")))
        .from(recordingInfo)
        .leftJoin(recordingFile)
        .on(recordingInfo.id.eq(recordingFile.recordingInfo.id)
            .and(recordingFile.isThumbnail.isTrue()))
        .leftJoin(recordingLicenseInfo)
        .on(recordingInfo.id.eq(recordingLicenseInfo.recordingInfo.id)
            .and(recordingLicenseInfo.licenseType.eq(10)))
        .leftJoin(usersFile)
        .on(recordingInfo.users.id.eq(usersFile.users.id)
            .and(usersFile.isProfile.isTrue()))
        .leftJoin(userMusician)
        .on(recordingInfo.users.id.eq(userMusician.users.id))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .where(
            query.getRecordingSort(recordingInfo)
                .and(recordingInfo.studioRegionCode.eq(query.getRegionCode(recordingInfo)))
                .and(recordingInfo.studioCityCode.eq(query.getCityCode(recordingInfo)))
        )
        .groupBy(recordingInfo.id,
            recordingInfo.serviceName,
            recordingInfo.isRecordingEngineering,
            recordingFile.url,
            recordingLicenseInfo.price,
            usersFile.url,
            userMusician.name)
        .orderBy(recordingInfo.createdAt.desc())
        .fetch();

    return new PageImpl<>(result, pageable, result.size());
  }
}
