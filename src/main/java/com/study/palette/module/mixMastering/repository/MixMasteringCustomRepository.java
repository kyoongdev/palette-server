package com.study.palette.module.mixMastering.repository;

import com.study.palette.module.mixMastering.dto.MixMasteringsResponseDto;
import com.study.palette.module.mixMastering.service.MixMasteringConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MixMasteringCustomRepository {

  Page<MixMasteringsResponseDto> findAll(MixMasteringConditions query, Pageable pageable);
}
