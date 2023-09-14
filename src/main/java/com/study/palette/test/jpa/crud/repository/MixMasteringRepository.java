package com.study.palette.test.jpa.crud.repository;

import com.study.palette.test.jpa.crud.model.MixMasteringInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MixMasteringRepository extends JpaRepository<MixMasteringInfo, String>, MixMasteringRepositoryCustom {

}
