package com.study.palette.module.mixMastering.repository;

import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MixMasteringRepository extends JpaRepository<MixMasteringInfo, String>,
    MixMasteringCustomRepository {

  Optional<MixMasteringInfo> findById(UUID id);
  long countByServiceStatus(boolean registrationCompleted);
  MixMasteringInfo findByServiceName(String serviceName);
}
