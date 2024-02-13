package com.study.palette.module.musician.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLTemplates;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.musician.ApprovalType;
import com.study.palette.module.albumArt.entity.QAlbumArtFile;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtLicenseInfo;
import com.study.palette.module.artist.entity.QArtistFile;
import com.study.palette.module.artist.entity.QArtistInfo;
import com.study.palette.module.artist.entity.QArtistLicenseInfo;
import com.study.palette.module.mixMastering.entity.QMixMasteringFile;
import com.study.palette.module.mixMastering.entity.QMixMasteringInfo;
import com.study.palette.module.mixMastering.entity.QMixMasteringLicenseInfo;
import com.study.palette.module.mrBeat.entity.QMrBeatFile;
import com.study.palette.module.mrBeat.entity.QMrBeatInfo;
import com.study.palette.module.mrBeat.entity.QMrBeatLicenseInfo;
import com.study.palette.module.musician.dto.MusicianReviewResponseDto;
import com.study.palette.module.musician.dto.MusicianSellingResponseDto;
import com.study.palette.module.musician.dto.query.FindMusicianSellingQuery;
import com.study.palette.module.recording.entity.QRecordingFile;
import com.study.palette.module.recording.entity.QRecordingInfo;
import com.study.palette.module.recording.entity.QRecordingLicenseInfo;
import com.study.palette.module.users.entity.QUsers;
import com.study.palette.module.users.entity.Users;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class UsersMusicianCustomRepositoryImpl implements UsersMusicianCustomRepository {

  private final EntityManager entityManager;

  private final SQLTemplates sqlTemplates;

  private final JPAQueryFactory queryFactory;

  @Autowired
  public UsersMusicianCustomRepositoryImpl(EntityManager entityManager,
      SQLTemplates sqlTemplates, JPAQueryFactory queryFactory) {
    this.entityManager = entityManager;
    this.sqlTemplates = sqlTemplates;
    this.queryFactory = queryFactory;
  }

  @Override
  public Long countBySellingCount(UUID id) {

    QArtistInfo artistInfo = QArtistInfo.artistInfo;
    QAlbumArtInfo albumArtInfo = QAlbumArtInfo.albumArtInfo;
    QMrBeatInfo mrBeatInfo = QMrBeatInfo.mrBeatInfo;
    QMixMasteringInfo mixMasteringInfo = QMixMasteringInfo.mixMasteringInfo;
    QRecordingInfo recordingInfo = QRecordingInfo.recordingInfo;

    long artistSellingCount = queryFactory.select(artistInfo.count())
        .from(artistInfo)
        .where(artistInfo.users.id.eq(id)
            .and(artistInfo.isSelling.isTrue())
            .and(artistInfo.approvalStatus.eq(ApprovalType.APPROVED)))
        .fetchOne();

    long albumArtSellingCount = queryFactory.select(albumArtInfo.count())
        .from(albumArtInfo)
        .where(albumArtInfo.users.id.eq(id)
            .and(albumArtInfo.isSelling.isTrue())
            .and(albumArtInfo.approvalStatus.eq(ApprovalType.APPROVED)))
        .fetchOne();

    long mrBeatSellingCount = queryFactory.select(mrBeatInfo.count())
        .from(mrBeatInfo)
        .where(mrBeatInfo.users.id.eq(id)
            .and(mrBeatInfo.isSelling.isTrue())
            .and(mrBeatInfo.approvalStatus.eq(ApprovalType.APPROVED)))
        .fetchOne();

    long mixMasteringSellingCount = queryFactory.select(mixMasteringInfo.count())
        .from(mixMasteringInfo)
        .where(mixMasteringInfo.users.id.eq(id)
            .and(mixMasteringInfo.isSelling.isTrue())
            .and(mixMasteringInfo.approvalStatus.eq(ApprovalType.APPROVED)))
        .fetchOne();

    long recordingSellingCount = queryFactory.select(recordingInfo.count())
        .from(recordingInfo)
        .where(recordingInfo.users.id.eq(id)
            .and(recordingInfo.isSelling.isTrue())
            .and(recordingInfo.approvalStatus.eq(ApprovalType.APPROVED)))
        .fetchOne();

    return artistSellingCount + albumArtSellingCount + mrBeatSellingCount + mixMasteringSellingCount + recordingSellingCount;

  }

  @Override
  public Long countByNotApprovedCount(UUID id) {

    QArtistInfo artistInfo = QArtistInfo.artistInfo;
    QAlbumArtInfo albumArtInfo = QAlbumArtInfo.albumArtInfo;
    QMrBeatInfo mrBeatInfo = QMrBeatInfo.mrBeatInfo;
    QMixMasteringInfo mixMasteringInfo = QMixMasteringInfo.mixMasteringInfo;
    QRecordingInfo recordingInfo = QRecordingInfo.recordingInfo;

    long artistReviewCount = queryFactory.select(artistInfo.count())
        .from(artistInfo)
        .where(artistInfo.users.id.eq(id)
            .and(artistInfo.isSelling.isFalse())
            .and(artistInfo.approvalStatus.notIn(ApprovalType.APPROVED)))
        .fetchOne();

    long albumArtReviewCount = queryFactory.select(albumArtInfo.count())
        .from(albumArtInfo)
        .where(albumArtInfo.users.id.eq(id)
            .and(albumArtInfo.isSelling.isFalse())
            .and(albumArtInfo.approvalStatus.notIn(ApprovalType.APPROVED)))
        .fetchOne();

    long mrBeatReviewCount = queryFactory.select(mrBeatInfo.count())
        .from(mrBeatInfo)
        .where(mrBeatInfo.users.id.eq(id)
            .and(mrBeatInfo.isSelling.isFalse())
            .and(mrBeatInfo.approvalStatus.notIn(ApprovalType.APPROVED))
            .and(mrBeatInfo.approvalStatus.notIn(ApprovalType.APPROVED)))
        .fetchOne();

    long mixMasteringReviewCount = queryFactory.select(mixMasteringInfo.count())
        .from(mixMasteringInfo)
        .where(mixMasteringInfo.users.id.eq(id)
            .and(mixMasteringInfo.isSelling.isFalse())
            .and(mixMasteringInfo.approvalStatus.notIn(ApprovalType.APPROVED)))
        .fetchOne();

    long recordingReviewCount = queryFactory.select(recordingInfo.count())
        .from(recordingInfo)
        .where(recordingInfo.users.id.eq(id)
            .and(recordingInfo.isSelling.isFalse())
            .and(recordingInfo.approvalStatus.notIn(ApprovalType.APPROVED)))
        .fetchOne();

    return artistReviewCount + albumArtReviewCount + mrBeatReviewCount + mixMasteringReviewCount + recordingReviewCount;

//    Query query = entityManager.createNativeQuery(
//        "SELECT (SELECT COUNT(*) FROM ArtistInfo where usersId = :usersId and isSelling = false) "
//            +
//            "+ (SELECT COUNT(*) FROM AlbumArtInfo where usersId = :usersId and isSelling = false) "
//            +
//            "+ (SELECT COUNT(*) FROM MrBeatInfo where usersId = :usersId and isSelling = false) "
//            +
//            "+ (SELECT COUNT(*) FROM MixMasteringInfo where usersId = :usersId and isSelling = false) "
//            +
//            "+ (SELECT COUNT(*) FROM RecordingInfo where usersId = :usersId and isSelling = false) ");
//
//    query.setParameter("usersId", id);
//
//    Long reviewCount = ((Number) query.getSingleResult()).longValue();
//
//    return reviewCount;
  }

  private BooleanExpression isExcludeTargetId(String excludeTargetId) {
    return excludeTargetId != null ? Expressions.template(UUID.class, "allServices.id")
        .ne(UUID.fromString(excludeTargetId)) : null;
  }

  @Override
  public Page<MusicianSellingResponseDto> getMusicianSellingList(FindMusicianSellingQuery query,
      Pageable pageable) {

    JPASQLQuery<MusicianSellingResponseDto> sqlQuery = new JPASQLQuery<>(entityManager,
        sqlTemplates);
    JPASQLQuery<MusicianSellingResponseDto> sqlQueryUnion = new JPASQLQuery<>(entityManager,
        sqlTemplates);

    QArtistInfo artistInfo = new QArtistInfo("ArtistInfo");
    QArtistFile artistFile = new QArtistFile("ArtistFile");
    QArtistLicenseInfo artistLicenseInfo = new QArtistLicenseInfo("ArtistLicenseInfo");

    QAlbumArtInfo albumArtInfo = new QAlbumArtInfo("AlbumArtInfo");
    QAlbumArtFile albumArtFile = new QAlbumArtFile("AlbumArtFile");
    QAlbumArtLicenseInfo albumArtLicenseInfo = new QAlbumArtLicenseInfo("AlbumArtLicenseInfo");

    QMrBeatInfo mrBeatInfo = new QMrBeatInfo("MrBeatInfo");
    QMrBeatFile mrBeatFile = new QMrBeatFile("MrBeatFile");
    QMrBeatLicenseInfo mrBeatLicenseInfo = new QMrBeatLicenseInfo("MrBeatLicenseInfo");

    QMixMasteringInfo mixMasteringInfo = new QMixMasteringInfo("MixMasteringInfo");
    QMixMasteringFile mixMasteringFile = new QMixMasteringFile("MixMasteringFile");
    QMixMasteringLicenseInfo mixMasteringLicenseInfo = new QMixMasteringLicenseInfo(
        "MixMasteringLicenseInfo");

    QRecordingInfo recordingInfo = new QRecordingInfo("RecordingInfo");
    QRecordingFile recordingFile = new QRecordingFile("RecordingFile");
    QRecordingLicenseInfo recordingLicenseInfo = new QRecordingLicenseInfo("RecordingLicenseInfo");

    QUsers users = new QUsers("Users");

    /* 서브쿼리 사용시 별칭 */
    StringPath subQueryAlias = Expressions.stringPath("sub_query_order");

    StringPath mrBeatLicenseInfoMrBeatInfoId = Expressions.stringPath(mrBeatLicenseInfo,
        "mrBeatInfoId");

    StringPath artistLicenseInfoArtistId = Expressions.stringPath(artistLicenseInfo,
        "artistInfoId");

    StringPath recordingLicenseInfoRecordingInfoId = Expressions.stringPath(recordingLicenseInfo,
        "recordingInfoId");

    StringPath mixMasteringLicenseInfoMixMasteringInfoId = Expressions.stringPath(
        mixMasteringLicenseInfo, "mixMasteringInfoId");

    StringPath albumArtLicenseInfoAlbumArtInfoId = Expressions.stringPath(albumArtLicenseInfo,
        "albumArtInfoId");

    JPASQLQuery<Tuple> sqlQuery2 = new JPASQLQuery<>(entityManager,
        sqlTemplates);
    List<Tuple> a = sqlQuery2.select(artistInfo.id, artistInfo.serviceInfo).from(artistInfo)
        .fetch();

    System.out.println(a.get(0).get(artistInfo.id));

    List<MusicianSellingResponseDto> result = sqlQuery
        .select(Projections.constructor(MusicianSellingResponseDto.class,
                Expressions.template(byte[].class, "allServices.id").as("id"),
                Expressions.stringTemplate("allServices.serviceName").as("serviceName"),
                Expressions.numberTemplate(Integer.class, "allServices.serviceType").as("serviceType"),
                Expressions.stringTemplate("allServices.name").as("name"),
                Expressions.stringTemplate("allServices.fileUrl").as("fileUrl"),
                Expressions.numberTemplate(Integer.class, "allServices.price").as("price"),
                Expressions.dateTemplate(Timestamp.class, "allServices.createdAt").as("createdAt")
            )
        )
        .from(
            sqlQueryUnion.unionAll(

                /* mrBeat */
                SQLExpressions
                    .select(
                        mrBeatInfo.id.as("id"),
                        mrBeatInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "4").as("serviceType"),
                        users.name.as("name"),
                        mrBeatFile.url.as("fileUrl"),
                        Expressions.template(Integer.class, "sub_query_order.price").as("price"),
                        mrBeatInfo.createdAt.as("createdAt")
                    )
                    .from(mrBeatInfo)
                    .join(mrBeatFile)
                    .on(mrBeatInfo.id.eq(
                        Expressions.path(UUID.class, mrBeatFile, "mrBeatInfoId")
                    ).and(mrBeatFile.isUse.isTrue()))
                    /* mixMasteringLicenseSubQuery select */
                    .join(JPAExpressions.select(
                                mrBeatLicenseInfo.price.min().as("price"),
                                mrBeatLicenseInfo.licenseType.min().as("licenseType"),
                                mrBeatLicenseInfoMrBeatInfoId
                            )
                            .from(mrBeatLicenseInfo)
                            .groupBy(
                                mrBeatLicenseInfoMrBeatInfoId
                            ), subQueryAlias
                    ).on(mrBeatInfo.id.eq(
                        Expressions.path(UUID.class, subQueryAlias, "mrBeatInfoId")
                    ))
                    .join(users)
                    .on(users.id.eq(
                        Expressions.path(UUID.class, mrBeatInfo, "usersId")
                    ))
                    .where(users.id.eq(UUID.fromString(query.getId())))
                    .where(mrBeatInfo.isSelling.isTrue()),

                /* artist */
                SQLExpressions
                    .select(
                        artistInfo.id.as("id"),
                        artistInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "2").as("serviceType"),
                        users.name.as("name"),
                        artistFile.url.as("fileUrl"),
                        Expressions.template(Integer.class, "sub_query_order.price").as("price"),
                        artistInfo.createdAt.as("createdAt")
                    )
                    .from(artistInfo)
                    .join(artistFile)
                    .on(artistInfo.id.eq(
                        Expressions.path(UUID.class, artistFile, "artistInfoId")
                    ).and(artistFile.isThumbnail.isTrue()))
                    .join(users)
                    .on(users.id.eq(
                        Expressions.path(UUID.class, artistInfo, "usersId")
                    ))
                    /* artistLicenseSubQuery select */
                    .join(JPAExpressions.select(
                                artistLicenseInfo.price.min().as("price"),
                                artistLicenseInfo.licenseType.min().as("licenseType"),
                                artistLicenseInfoArtistId
                            )
                            .from(artistLicenseInfo)
                            .groupBy(
                                artistLicenseInfoArtistId
                            ), subQueryAlias
                    ).on(artistInfo.id.eq(
                        Expressions.path(UUID.class, subQueryAlias, "artistInfoId")
                    ))
                    .where(users.id.eq(UUID.fromString(query.getId())))
                    .where(artistInfo.isSelling.isTrue()),

                /* recording */
                SQLExpressions
                    .select(
                        recordingInfo.id.as("id"),
                        recordingInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "3").as("serviceType"),
                        users.name.as("name"),
                        recordingFile.uploadFileName.as("fileUrl"),
                        Expressions.template(Integer.class, "sub_query_order.price").as("price"),
                        recordingInfo.createdAt.as("createdAt")
                    )
                    .from(recordingInfo)
                    .join(users)
                    .on(users.id.eq(
                        Expressions.path(UUID.class, recordingInfo, "usersId")
                    ))
                    .join(recordingFile)
                    .on(recordingInfo.id.eq(
                        Expressions.path(UUID.class, recordingFile, "recordingInfoId")
                    ).and(recordingFile.isThumbnail.isTrue()))

                    /* recordingLicenseSubQuery select */
                    .join(
                        JPAExpressions.select(
                                recordingLicenseInfo.price.min().as("price"),
                                recordingLicenseInfo.licenseType.min().as("licenseType"),
                                recordingLicenseInfoRecordingInfoId
                            )
                            .from(recordingLicenseInfo)
                            .groupBy(
                                recordingLicenseInfoRecordingInfoId
                            ), subQueryAlias
                    ).on(recordingInfo.id.eq(
                        Expressions.path(UUID.class, subQueryAlias, "recordingInfoId")
                    ))
                    .where(users.id.eq(UUID.fromString(query.getId())))
                    .where(recordingFile.isThumbnail.isTrue())
                    .where(recordingInfo.isSelling.isTrue()),

                /*mixMastering*/
                SQLExpressions
                    .select(
                        mixMasteringInfo.id.as("id"),
                        mixMasteringInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "1").as("serviceType"),
                        users.name.as("name"),
                        mixMasteringFile.uploadFileName.as("fileUrl"),
                        Expressions.template(Integer.class, "sub_query_order.price").as("price"),
                        mixMasteringInfo.createdAt.as("createdAt")
                    )
                    .from(mixMasteringInfo)
                    .join(users)
                    .on(users.id.eq(
                        Expressions.path(UUID.class, mixMasteringInfo, "usersId")
                    ))
                    .join(mixMasteringFile)
                    .on(mixMasteringInfo.id.eq(
                        Expressions.path(UUID.class, mixMasteringFile, "mixMasteringInfoId")
                    ).and(mixMasteringFile.isThumbnail.isTrue()))

                    /* mixMasteringLicenseSubQuery select */
                    .join(JPAExpressions.select(
                                mixMasteringLicenseInfo.price.min().as("price"),
                                mixMasteringLicenseInfo.licenseType.min().as("licenseType"),
                                mixMasteringLicenseInfoMixMasteringInfoId
                            )
                            .from(mixMasteringLicenseInfo)
                            .groupBy(
                                mixMasteringLicenseInfoMixMasteringInfoId
                            ), subQueryAlias
                    ).on(mixMasteringInfo.id.eq(
                        Expressions.path(UUID.class, subQueryAlias, "mixMasteringInfoId")
                    ))
                    .where(users.id.eq(UUID.fromString(query.getId())))
                    .where(mixMasteringInfo.isSelling.isTrue()),

                /*albumArt*/
                SQLExpressions
                    .select(
                        albumArtInfo.id.as("id"),
                        albumArtInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "5").as("serviceType"),
                        users.name.as("name"),
                        albumArtFile.url.as("fileUrl"),
                        Expressions.template(Integer.class, "sub_query_order.price").as("price"),
                        albumArtInfo.createdAt.as("createdAt")
                    )
                    .from(albumArtInfo)
                    .join(albumArtFile)
                    .on(
                        albumArtInfo.id.eq(
                            Expressions.path(UUID.class, albumArtFile, "albumArtInfoId")
                        ).and(albumArtFile.isThumbnail.isTrue()))

                    /* albumArtLicenseSubQuery select */
                    .join(JPAExpressions.select(
                                albumArtLicenseInfo.price.min().as("price"),
                                albumArtLicenseInfo.licenseType.min().as("licenseType"),
                                albumArtLicenseInfoAlbumArtInfoId
                            )
                            .from(albumArtLicenseInfo)
                            .groupBy(
                                albumArtLicenseInfoAlbumArtInfoId
                            ), subQueryAlias
                    ).on(albumArtInfo.id.eq(
                        Expressions.path(UUID.class, subQueryAlias, "albumArtInfoId")
                    ))
                    .join(users).on(
                        users.id.eq(
                            Expressions.path(UUID.class, albumArtInfo, "usersId")
                        ))
                    .where(users.id.eq(UUID.fromString(query.getId())))
                    .where(albumArtFile.isThumbnail.isTrue())
                    .where(albumArtInfo.isSelling.isTrue())
            ).as("allServices"))
        .where(isExcludeTargetId(query.getExcludeTargetId()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(Expressions.dateTemplate(Timestamp.class, "allServices.createdAt").desc())
        .fetch();

    return new PageImpl<>(result, pageable, result.size());
  }


  @Override
  public Page<MusicianReviewResponseDto> getMusicianReviewList(PageDto query,
      Pageable pageable, Users users) {

    JPASQLQuery<MusicianSellingResponseDto> sqlQuery = new JPASQLQuery<>(entityManager,
        sqlTemplates);
    JPASQLQuery<MusicianSellingResponseDto> sqlQueryUnion = new JPASQLQuery<>(entityManager,
        sqlTemplates);

    QArtistInfo artistInfo = new QArtistInfo("ArtistInfo");

    QAlbumArtInfo albumArtInfo = new QAlbumArtInfo("AlbumArtInfo");

    QMrBeatInfo mrBeatInfo = new QMrBeatInfo("MrBeatInfo");

    QMixMasteringInfo mixMasteringInfo = new QMixMasteringInfo("MixMasteringInfo");

    QRecordingInfo recordingInfo = new QRecordingInfo("RecordingInfo");

    List<MusicianReviewResponseDto> result = sqlQuery
        .select(Projections.constructor(MusicianReviewResponseDto.class,
                Expressions.template(byte[].class, "allServices.id").as("id"),
                Expressions.stringTemplate("allServices.serviceName").as("serviceName"),
                Expressions.numberTemplate(Integer.class, "allServices.serviceType").as("serviceType"),
                Expressions.numberTemplate(Integer.class, "allServices.approvalStatus")
                    .as("approvalStatus"),
                Expressions.dateTemplate(Timestamp.class, "allServices.createdAt").as("createdAt")
            )
        )
        .from(
            sqlQueryUnion.unionAll(
                SQLExpressions
                    .select(
                        mrBeatInfo.id.as("id"),
                        mrBeatInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "4").as("serviceType"),
                        mrBeatInfo.approvalStatus.as("approvalStatus"),
                        mrBeatInfo.createdAt.as("createdAt")
                    )
                    .from(mrBeatInfo)
                    .where(
                        Expressions.path(UUID.class, mrBeatInfo, "usersId").eq(users.getId())
                    )
                    .where(mrBeatInfo.isSelling.isFalse()),
                SQLExpressions
                    .select(
                        artistInfo.id.as("id"),
                        artistInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "2").as("serviceType"),
                        artistInfo.approvalStatus.as("approvalStatus"),
                        artistInfo.createdAt.as("createdAt")
                    )
                    .from(artistInfo)
                    .where(
                        Expressions.path(UUID.class, artistInfo, "usersId").eq(users.getId())
                    )
                    .where(artistInfo.isSelling.isFalse()),
                SQLExpressions
                    .select(
                        recordingInfo.id.as("id"),
                        recordingInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "3").as("serviceType"),
                        recordingInfo.approvalStatus.as("approvalStatus"),
                        recordingInfo.createdAt.as("createdAt")
                    )
                    .from(recordingInfo)
                    .where(
                        Expressions.path(UUID.class, recordingInfo, "usersId").eq(users.getId())
                    )
                    .where(recordingInfo.isSelling.isFalse()),
                SQLExpressions
                    .select(
                        mixMasteringInfo.id.as("id"),
                        mixMasteringInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "1").as("serviceType"),
                        mixMasteringInfo.approvalStatus.as("approvalStatus"),
                        mixMasteringInfo.createdAt.as("createdAt")
                    )
                    .from(mixMasteringInfo)
                    .where(
                        Expressions.path(UUID.class, mixMasteringInfo, "usersId").eq(users.getId())
                    )
                    .where(mixMasteringInfo.isSelling.isFalse()),

                SQLExpressions
                    .select(
                        albumArtInfo.id.as("id"),
                        albumArtInfo.serviceName.as("serviceName"),
                        Expressions.numberTemplate(Integer.class, "5").as("serviceType"),
                        albumArtInfo.approvalStatus.as("approvalStatus"),
                        albumArtInfo.createdAt.as("createdAt")
                    )
                    .from(albumArtInfo)
                    .where(
                        Expressions.path(UUID.class, albumArtInfo, "usersId").eq(users.getId())
                    )
                    .where(albumArtInfo.isSelling.isFalse())
            ).as("allServices"))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(Expressions.dateTemplate(Timestamp.class, "allServices.createdAt").desc())
        .fetch();

    System.out.println(result);

    return new PageImpl<>(result, pageable, result.size());
  }


}


