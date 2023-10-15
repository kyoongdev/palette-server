package com.study.palette.module.filter.repository;

import com.study.palette.module.filter.entity.FilterMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterMasterRepository extends JpaRepository<FilterMaster, Integer> {
}
