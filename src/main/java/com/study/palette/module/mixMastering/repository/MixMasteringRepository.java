package com.study.palette.module.mixMastering.repository;

import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MixMasteringRepository extends JpaRepository<MixMasteringInfo, String> {
  Page<MixMasteringInfo> findAll(Pageable pageable);
}
