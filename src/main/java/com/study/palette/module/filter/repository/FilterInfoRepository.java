package com.study.palette.module.filter.repository;

import com.study.palette.module.filter.entity.FilterInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterInfoRepository extends JpaRepository<FilterInfo, String> {
    List<FilterInfo> findAllByKey_Key(int i);
}
