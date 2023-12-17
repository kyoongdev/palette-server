package com.study.palette.module.adminSales.repository;

import com.study.palette.module.adminSales.dto.AdminSalesResponseDto;
import com.study.palette.module.adminSales.service.AdminSalesConditions;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import com.study.palette.module.recording.entity.RecordingInfo;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class AdminSalesCustomRepositoryImpl implements AdminSalesCustomRepository {

  private final EntityManager entityManager;

  @Autowired
  public AdminSalesCustomRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<AdminSalesResponseDto> findAllByServiceStatusAndCreatedAtDesc(AdminSalesConditions query, Pageable pageable) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<AdminSalesResponseDto> mainQuery = cb.createQuery(AdminSalesResponseDto.class);
    Root<AdminSalesResponseDto> root = mainQuery.from(AdminSalesResponseDto.class);

    //5개의 entity union
    //1. albumArt
//    CriteriaQuery<AdminSalesResponseDto> albumArtQuery = cb.createQuery(AdminSalesResponseDto.class);
//    Root<AlbumArtInfo> albumArtInfoRoot = albumArtQuery.from(AlbumArtInfo.class);
//    albumArtQuery.where(
//            cb.equal(albumArtInfoRoot.get("serviceStatus"), query.getRecordingSort())
//        )
//        .orderBy(cb.desc(albumArtInfoRoot.get("createdAt")));

//    //2. mix mastering
//    CriteriaQuery<AdminSalesResponseDto> mixMasteringQuery = cb.createQuery(AdminSalesResponseDto.class);
//    Root<MixMasteringInfo> mixMasteringInfoRoot = mixMasteringQuery.from(MixMasteringInfo.class);
//    mixMasteringQuery.where(
//            cb.equal(mixMasteringInfoRoot.get("serviceStatus"), query.getRecordingSort())
//        )
//        .orderBy(cb.desc(mixMasteringInfoRoot.get("createdAt")));

    //3. recording
//    CriteriaQuery<AdminSalesResponseDto> recordingQuery = cb.createQuery(AdminSalesResponseDto.class);
//    Root<RecordingInfo> recordingInfoRoot = recordingQuery.from(RecordingInfo.class);
//    recordingQuery.where(
//            cb.equal(recordingInfoRoot.get("serviceStatus"), query.getRecordingSort())
//        )
//        .orderBy(cb.desc(recordingInfoRoot.get("createdAt")));

    //4. artist
//    CriteriaQuery<AdminSalesResponseDto> artistQuery = cb.createQuery(AdminSalesResponseDto.class);
//    Root<ArtistInfo> artistInfoRoot = artistQuery.from(ArtistInfo.class);
//    artistQuery.where(
//            cb.equal(artistInfoRoot.get("serviceStatus"), query.getRecordingSort())
//        )
//        .orderBy(cb.desc(artistInfoRoot.get("createdAt")));

    //5. mrBeat
//    CriteriaQuery<AdminSalesResponseDto> mrBeatQuery = cb.createQuery(AdminSalesResponseDto.class);
//    Root<MrBeatInfo> mrBeatInfoRoot = mrBeatQuery.from(MrBeatInfo.class);
//    mrBeatQuery.where(
//            cb.equal(mrBeatInfoRoot.get("serviceStatus"), query.getRecordingSort())
//        )
//        .orderBy(cb.desc(mrBeatInfoRoot.get("createdAt")));

    //1. albumArt
    Subquery<AlbumArtInfo> albumArtQuery = cb.createQuery().subquery(AlbumArtInfo.class);
    Root<AlbumArtInfo> albumArtInfoRoot = albumArtQuery.from(AlbumArtInfo.class);

    albumArtQuery.select(
        albumArtInfoRoot.get("serviceName"),
        albumArtInfoRoot.get("salesType"),
        albumArtInfoRoot.get("createdAt"),
        cb.function("registerDeadline", Date.class, albumArtInfoRoot.get("createdAt"), cb.literal(5))
    )


    albumArtQuery.where(
            cb.equal(albumArtInfoRoot.get("serviceStatus"), query.getRecordingSort())
        );

    //2. mix mastering
    Subquery<AdminSalesResponseDto> mixMasteringQuery = cb.createQuery().subquery(AdminSalesResponseDto.class);
    Root<MixMasteringInfo> mixMasteringInfoRoot = mixMasteringQuery.from(MixMasteringInfo.class);
    mixMasteringQuery.where(
            cb.equal(mixMasteringInfoRoot.get("serviceStatus"), query.getRecordingSort())
        );

    mainQuery.select(
        root.get("serviceName"),
        root.get("salesType"),
        root.get("createdAt"),
        root.get("registerDeadline")
    )


    cb.unionuni(albumArtQuery, mixMasteringQuery, recordingQuery, artistQuery, mrBeatQuery);
    //5개의 entity union
    cbQuery.select(cb.construct(AdminSalesResponseDto.class,
        albumArtInfoRoot.get("serviceName"),
        cb.literal("앨범아트").alias("salesType"),
        albumArtInfoRoot.get("createdAt"),
        cb.function("registerDeadline", Date.class, albumArtInfoRoot.get("createdAt"), cb.literal(5)),
//        artistInfoRoot.get("serviceName"),
//        cb.literal("아티스트").alias("salesType"),
//        artistInfoRoot.get("createdAt"),
//        cb.function("registerDeadline", Date.class, artistInfoRoot.get("createdAt"), cb.literal(5)),
        mixMasteringInfoRoot.get("serviceName"),
        cb.literal("믹스&마스터링").alias("salesType"),
        mixMasteringInfoRoot.get("createdAt"),
        cb.function("registerDeadline", Date.class, mixMasteringInfoRoot.get("createdAt"), cb.literal(5)),
//        mrBeatInfoRoot.get("serviceName"),
//        cb.literal("작곡").alias("salesType"),
//        mrBeatInfoRoot.get("createdAt"),
//        cb.function("registerDeadline", Date.class, mrBeatInfoRoot.get("createdAt"), cb.literal(5)),
        recordingInfoRoot.get("serviceName"),
        cb.literal("녹음"),
        recordingInfoRoot.get("createdAt"),
        cb.function("registerDeadline", Date.class, recordingInfoRoot.get("createdAt"), cb.literal(5))
    ));

    TypedQuery<AdminSalesResponseDto> typedQuery = entityManager.createQuery(cbQuery);
    typedQuery.setFirstResult(pageable.getPageNumber());
    typedQuery.setMaxResults(pageable.getPageSize());
    return typedQuery.getResultList();
  }
}
