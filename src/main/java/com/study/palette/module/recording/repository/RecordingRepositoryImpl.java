package com.study.palette.module.recording.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.palette.module.recording.dto.info.RecordingResponseDto;
import com.study.palette.module.recording.dto.query.FindRecordingQuery;
import com.study.palette.module.recording.entity.QRecordingFile;
import com.study.palette.module.recording.entity.QRecordingInfo;
import com.study.palette.module.recording.entity.QRecordingLicenseInfo;
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
  public Page<RecordingResponseDto> findAll(FindRecordingQuery query, Pageable pageable) {
    QRecordingInfo recordingInfo = QRecordingInfo.recordingInfo;
    QRecordingFile recordingFile = QRecordingFile.recordingFile;
    QRecordingLicenseInfo recordingLicenseInfo = QRecordingLicenseInfo.recordingLicenseInfo;

    List<RecordingResponseDto> result = queryFactory
        .select(Projections.constructor(RecordingResponseDto.class,
            recordingInfo.id,
            recordingInfo.serviceName,
            recordingInfo.salesType,
            recordingInfo.users.name.as("userName"),
            recordingFile.upoladFilePath.as("fileUrl"),
            recordingLicenseInfo.price.as("price")))
        .from(recordingInfo)
        .leftJoin(recordingFile)
        .on(recordingInfo.id.eq(recordingFile.recordingInfo.id)
            .and(recordingFile.isThumbnail.isTrue()))
        .leftJoin(recordingLicenseInfo)
        .on(recordingInfo.id.eq(recordingLicenseInfo.recordingInfo.id)
            .and(recordingLicenseInfo.licenseType.eq(10)))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .where(query.getRecordingSort(recordingInfo))
        .groupBy(recordingInfo.id,
            recordingInfo.serviceName,
            recordingInfo.salesType,
            recordingInfo.users.name,
            recordingFile.upoladFilePath,
            recordingLicenseInfo.price)
        .orderBy(recordingInfo.createdAt.desc())
        .fetch();

    return new PageImpl<>(result, pageable, result.size());
  }
}
