package com.study.palette.module.mainHome.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLTemplates;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtRequest;
import com.study.palette.module.artist.entity.QArtistInfo;
import com.study.palette.module.artist.entity.QArtistRequest;
import com.study.palette.module.mainHome.dto.MusicianRankDto;
import com.study.palette.module.mainHome.dto.query.FindTopMusicianQuery;
import com.study.palette.module.mixMastering.entity.QMixMasteringInfo;
import com.study.palette.module.mixMastering.entity.QMixMasteringRequest;
import com.study.palette.module.mrBeat.entity.QMrBeatInfo;
import com.study.palette.module.mrBeat.entity.QMrBeatRequest;
import com.study.palette.module.musician.entity.QUsersMusician;
import com.study.palette.module.users.entity.QUsersFile;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MainHomeRepositoryImpl implements MainHomeRepository {

  private final SQLTemplates sqlTemplates;

  private final EntityManager entityManager;

  @Autowired
  public MainHomeRepositoryImpl(SQLTemplates sqlTemplates, EntityManager entityManager) {
    this.sqlTemplates = sqlTemplates;
    this.entityManager = entityManager;
  }


  @Override
  public List<MusicianRankDto> getAlbumArtRanks(FindTopMusicianQuery query) {
    JPASQLQuery<MusicianRankDto> sqlQuery = new JPASQLQuery<>(entityManager, sqlTemplates);

    StringPath subQueryAlias = Expressions.stringPath("AlbumArtRequest");

    QAlbumArtInfo albumArtInfo = new QAlbumArtInfo("AlbumArtInfo");
    QAlbumArtRequest albumArtRequest = new QAlbumArtRequest("AlbumArtRequest");
    QUsersMusician usersMusician = new QUsersMusician("UsersMusician");
    QUsersFile usersFile = new QUsersFile("UsersFile");

    LocalDateTime firstDayOfMonth = LocalDate.now().atStartOfDay();
    LocalDateTime lastDayOfMonth = LocalDate.now().plusMonths(1).atStartOfDay().minusSeconds(1);

    List<MusicianRankDto> result = sqlQuery
        .select(Projections.constructor(MusicianRankDto.class,
            Expressions.template(byte[].class, "UsersMusician.id"),
//            usersMusician.id.as("id"),
            SQLExpressions.rowNumber().over().orderBy(usersMusician.id.count()).as("rankNumber"),
            usersFile.uploadFilePath.as("profileUrl"),
            usersMusician.name.as("musicianName")
        ))
        .from(usersMusician)
        .leftJoin(albumArtInfo)
        .on(Expressions.template(UUID.class, "UsersMusician.usersId").eq(Expressions.template(UUID.class, "AlbumArtInfo.usersId"))
            .and(albumArtInfo.isSelling.eq(true)))
        .leftJoin(
            JPAExpressions
                .selectDistinct(
                    Expressions.template(UUID.class, "AlbumArtRequest.usersId").as("usersId"),
                    Expressions.template(UUID.class, "AlbumArtRequest.albumArtInfoId").as("albumArtInfoId")
                )
                .from(albumArtRequest)
                .where(albumArtRequest.createdAt.between(firstDayOfMonth, lastDayOfMonth))
            , subQueryAlias
        )
        .on(Expressions.template(UUID.class, "AlbumArtInfo.id").eq(Expressions.template(UUID.class, "AlbumArtRequest.albumArtInfoId")))
        .leftJoin(usersFile)
        .on(Expressions.template(UUID.class, "AlbumArtInfo.usersId").eq(Expressions.template(UUID.class, "UsersFile.usersId"))
            .and(usersFile.isProfile.eq(true)))
        .groupBy(usersMusician.id, usersFile.uploadFilePath, usersMusician.name)
        .limit(5)
        .fetch();

    return result;
  }

  @Override
  public List<MusicianRankDto> getArtistRanks(FindTopMusicianQuery query) {
    JPASQLQuery<MusicianRankDto> sqlQuery = new JPASQLQuery<>(entityManager, sqlTemplates);

    StringPath subQueryAlias = Expressions.stringPath("ArtistRequest");

    QArtistInfo artistInfo = new QArtistInfo("ArtistInfo");
    QArtistRequest artistRequest = new QArtistRequest("ArtistRequest");
    QUsersMusician usersMusician = new QUsersMusician("UsersMusician");
    QUsersFile usersFile = new QUsersFile("UsersFile");

    LocalDateTime firstDayOfMonth = LocalDate.now().atStartOfDay();
    LocalDateTime lastDayOfMonth = LocalDate.now().plusMonths(1).atStartOfDay().minusSeconds(1);

    List<MusicianRankDto> result = sqlQuery.select(Projections.constructor(MusicianRankDto.class,
            Expressions.template(byte[].class, "UsersMusician.id"),
            SQLExpressions.rowNumber().over().orderBy(usersMusician.id.count()).as("rankNumber"),
            usersFile.uploadFilePath.as("profileUrl"),
            usersMusician.name.as("musicianName")
        ))
        .from(usersMusician)
        .leftJoin(artistInfo)
        .on(Expressions.template(UUID.class, "UsersMusician.usersId").eq(Expressions.template(UUID.class, "ArtistInfo.usersId"))
            .and(artistInfo.isSelling.eq(true)))
        .leftJoin(
            JPAExpressions
                .selectDistinct(
                    Expressions.template(UUID.class, "ArtistRequest.usersId").as("usersId"),
                    Expressions.template(UUID.class, "ArtistRequest.artistInfoId").as("artistInfoId")
                )
                .from(artistRequest)
                .where(artistRequest.createdAt.between(firstDayOfMonth, lastDayOfMonth))
            , subQueryAlias
        )
        .on(Expressions.template(UUID.class, "ArtistInfo.id").eq(Expressions.template(UUID.class, "ArtistRequest.artistInfoId")))
        .leftJoin(usersFile)
        .on(Expressions.template(UUID.class, "ArtistInfo.usersId").eq(Expressions.template(UUID.class, "UsersFile.usersId"))
            .and(usersFile.isProfile.eq(true)))
        .groupBy(usersMusician.id, usersFile.uploadFilePath, usersMusician.name)
        .limit(5)
        .fetch();

    return result;
  }

  @Override
  public List<MusicianRankDto> getRecordingRanks(FindTopMusicianQuery query) {
    // TODO 레코딩에는 순위를 매기기 위한 구매의뢰란 버튼이 없음 잠시 보류
    return null;
  }

  @Override
  public List<MusicianRankDto> getMrBeatRanks(FindTopMusicianQuery query) {
    JPASQLQuery<MusicianRankDto> sqlQuery = new JPASQLQuery<>(entityManager, sqlTemplates);

    StringPath subQueryAlias = Expressions.stringPath("MrBeatRequest");

    QMrBeatInfo mrBeatInfo = new QMrBeatInfo("MrBeatInfo");
    QMrBeatRequest mrBeatRequest = new QMrBeatRequest("MrBeatRequest");
    QUsersMusician usersMusician = new QUsersMusician("UsersMusician");
    QUsersFile usersFile = new QUsersFile("UsersFile");

    LocalDateTime firstDayOfMonth = LocalDate.now().atStartOfDay();
    LocalDateTime lastDayOfMonth = LocalDate.now().plusMonths(1).atStartOfDay().minusSeconds(1);

    List<MusicianRankDto> result = sqlQuery.select(Projections.constructor(MusicianRankDto.class,
            Expressions.template(byte[].class, "UsersMusician.id"),
            SQLExpressions.rowNumber().over().orderBy(usersMusician.id.count()).as("rankNumber"),
            usersFile.uploadFilePath.as("profileUrl"),
            usersMusician.name.as("musicianName")
        ))
        .from(usersMusician)
        .leftJoin(mrBeatInfo)
        .on(Expressions.template(UUID.class, "UsersMusician.usersId").eq(Expressions.template(UUID.class, "MrBeatInfo.usersId"))
            .and(mrBeatInfo.isSelling.eq(true)))
        .leftJoin(
            JPAExpressions
                .selectDistinct(
                    Expressions.template(UUID.class, "MrBeatRequest.usersId").as("usersId"),
                    Expressions.template(UUID.class, "MrBeatRequest.mrBeatInfoId").as("mrBeatInfoId")
                )
                .from(mrBeatRequest)
//                .where(mrBeatRequest.createdAt.between(firstDayOfMonth, lastDayOfMonth)) TODO 타입 에러 LocalDate -> LocalDateTime 변경필요
            , subQueryAlias
        )
        .on(Expressions.template(UUID.class, "MrBeatInfo.id").eq(Expressions.template(UUID.class, "MrBeatRequest.mrBeatInfoId")))
        .leftJoin(usersFile)
        .on(Expressions.template(UUID.class, "MrBeatInfo.usersId").eq(Expressions.template(UUID.class, "UsersFile.usersId"))
            .and(usersFile.isProfile.eq(true)))
        .groupBy(usersMusician.id, usersFile.uploadFilePath, usersMusician.name)
        .limit(5)
        .fetch();

    return result;
  }

  @Override
  public List<MusicianRankDto> getMixMasteringRanks(FindTopMusicianQuery query) {
    JPASQLQuery<MusicianRankDto> sqlQuery = new JPASQLQuery<>(entityManager, sqlTemplates);

    StringPath subQueryAlias = Expressions.stringPath("MixMasteringRequest");

    QMixMasteringInfo mixMasteringInfo = new QMixMasteringInfo("MixMasteringInfo");
    QMixMasteringRequest mixMasteringRequest = new QMixMasteringRequest("MixMasteringRequest");
    QUsersMusician usersMusician = new QUsersMusician("UsersMusician");
    QUsersFile usersFile = new QUsersFile("UsersFile");

    LocalDateTime firstDayOfMonth = LocalDate.now().atStartOfDay();
    LocalDateTime lastDayOfMonth = LocalDate.now().plusMonths(1).atStartOfDay().minusSeconds(1);

    List<MusicianRankDto> result = sqlQuery.select(Projections.constructor(MusicianRankDto.class,
            Expressions.template(byte[].class, "UsersMusician.id"),
            SQLExpressions.rowNumber().over().orderBy(usersMusician.id.count()).as("rankNumber"),
            usersFile.uploadFilePath.as("profileUrl"),
            usersMusician.name.as("musicianName")
        ))
        .from(usersMusician)
        .leftJoin(mixMasteringInfo)
        .on(Expressions.template(UUID.class, "UsersMusician.usersId").eq(Expressions.template(UUID.class, "MixMasteringInfo.usersId"))
            .and(mixMasteringInfo.isSelling.eq(true)))
        .leftJoin(
            JPAExpressions
                .selectDistinct(
                    Expressions.template(UUID.class, "MixMasteringRequest.usersId").as("usersId"),
                    Expressions.template(UUID.class, "MixMasteringRequest.mixMasteringInfoId").as("mixMasteringInfoId")
                )
                .from(mixMasteringRequest)
                .where(mixMasteringRequest.createdAt.between(firstDayOfMonth, lastDayOfMonth))
            , subQueryAlias
        )
        .on(Expressions.template(UUID.class, "MixMasteringInfo.id").eq(Expressions.template(UUID.class, "MixMasteringRequest.mixMasteringInfoId")))
        .leftJoin(usersFile)
        .on(Expressions.template(UUID.class, "MixMasteringInfo.usersId").eq(Expressions.template(UUID.class, "UsersFile.usersId"))
            .and(usersFile.isProfile.eq(true)))
        .groupBy(usersMusician.id, usersFile.uploadFilePath, usersMusician.name)
        .limit(5)
        .fetch();

    return result;
  }
}
