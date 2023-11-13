package com.study.palette.module.serviceProgress.repository;

import com.study.palette.module.serviceProgress.entity.ServiceProgressInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProgressInfoRepository extends JpaRepository<ServiceProgressInfo, String>, JpaSpecificationExecutor<ServiceProgressInfo> {
}
