package com.study.palette.module.mixMastering.repository;

import com.study.palette.module.mixMastering.dto.MixMasteringsDto;
import com.study.palette.module.mixMastering.dto.query.FindMixMasteringQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MixMasteringCustomRepository {

  Page<MixMasteringsDto> findAll(FindMixMasteringQuery query, Pageable pageable);
}
