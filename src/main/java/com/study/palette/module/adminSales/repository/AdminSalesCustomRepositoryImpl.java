package com.study.palette.module.adminSales.repository;

import com.study.palette.module.adminSales.dto.AdminSalesResponseDto;
import com.study.palette.module.adminSales.dto.FindAdminSalesQuery;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class AdminSalesCustomRepositoryImpl extends QuerydslRepositorySupport implements AdminSalesCustomRepository {

  private final EntityManager entityManager;

  @Autowired
  public AdminSalesCustomRepositoryImpl(EntityManager entityManager) {
    super(AdminSalesResponseDto.class);
    this.entityManager = entityManager;
  }

  @Override
  public List<AdminSalesResponseDto> findAllByServiceStatusAndCreatedAtDesc(FindAdminSalesQuery query, Pageable pageable) {
//  }
//    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//    CriteriaQuery<AdminSalesResponseDto> cbQuery = cb.createQuery(AdminSalesResponseDto.class);
//
//    Root<AlbumArtInfo> albumArtInfoRoot = cbQuery.from(AlbumArtInfo.class);
//    Root<ArtistInfo> artistInfoRoot = cbQuery.from(ArtistInfo.class);
//    Root<MixMasteringInfo> mixMasteringInfoRoot = cbQuery.from(MixMasteringInfo.class);
//    Root<MrBeatInfo> mrBeatInfoRoot = cbQuery.from(MrBeatInfo.class);
//    Root<RecordingInfo> recordingInfoRoot = cbQuery.from(RecordingInfo.class);
//
//    //조건
//    Predicate albumArtPredicate = cb.equal(albumArtInfoRoot.get("serviceStatus"), query.isRegistrationCompleted());
//    Predicate artistPredicate = cb.equal(artistInfoRoot.get("serviceStatus"), query.isRegistrationCompleted());
//    Predicate mixMasteringPredicate = cb.equal(mixMasteringInfoRoot.get("serviceStatus"), query.isRegistrationCompleted());
//    Predicate mrBeatPredicate = cb.equal(mrBeatInfoRoot.get("serviceStatus"), query.isRegistrationCompleted());
//    Predicate recordingPredicate = cb.equal(recordingInfoRoot.get("serviceStatus"), query.isRegistrationCompleted());
//    cbQuery.where(albumArtPredicate).orderBy(cb.desc(albumArtInfoRoot.get("createdAt")));
//    cbQuery.where(artistPredicate).orderBy(cb.desc(artistInfoRoot.get("createdAt")));
//    cbQuery.where(mixMasteringPredicate).orderBy(cb.desc(mixMasteringInfoRoot.get("createdAt")));
//    cbQuery.where(mrBeatPredicate).orderBy(cb.desc(mrBeatInfoRoot.get("createdAt")));
//    cbQuery.where(recordingPredicate).orderBy(cb.desc(recordingInfoRoot.get("createdAt")));
//
//    //5개의 entity union
//    cbQuery.select(cb.construct(AdminSalesResponseDto.class,
//        albumArtInfoRoot.get("serviceName"),
//        albumArtInfoRoot.get("serviceType"),
//        albumArtInfoRoot.get("createdAt"),
//        albumArtInfoRoot.get("registerDeadline"),
//        artistInfoRoot.get("serviceName"),
//        artistInfoRoot.get("serviceType"),
//        artistInfoRoot.get("createdAt"),
//        artistInfoRoot.get("registerDeadline"),
//        mixMasteringInfoRoot.get("serviceName"),
//        mixMasteringInfoRoot.get("serviceType"),
//        mixMasteringInfoRoot.get("createdAt"),
//        mixMasteringInfoRoot.get("registerDeadline"),
//        mrBeatInfoRoot.get("serviceName"),
//        mrBeatInfoRoot.get("serviceType"),
//        mrBeatInfoRoot.get("createdAt"),
//        mrBeatInfoRoot.get("registerDeadline"),
//        recordingInfoRoot.get("serviceName"),
//        recordingInfoRoot.get("serviceType"),
//        recordingInfoRoot.get("createdAt"),
//        recordingInfoRoot.get("registerDeadline")
//    ));
//
//    TypedQuery<AdminSalesResponseDto> typedQuery = entityManager.createQuery(cbQuery);
//    typedQuery.setFirstResult(pageable.getPageNumber());
//    typedQuery.setMaxResults(pageable.getPageSize());
//    return typedQuery.getResultList();
  }
}
