package com.study.palette.module.mixMastering.service;


import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.mixMastering.dto.MixMasteringDto;
import com.study.palette.module.mixMastering.repository.MixMasteringRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MixMasteringService {

  private final MixMasteringRepository mixMasteringRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public MixMasteringService(MixMasteringRepository mixMasteringRepository, ModelMapper modelMapper) {
    this.mixMasteringRepository = mixMasteringRepository;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public PaginationDto<MixMasteringDto> getMixMasterings(Pageable pageable) {
    Long count = mixMasteringRepository.count();
    List<MixMasteringDto> mixMasterings = mixMasteringRepository.findAll(pageable).stream()
            .map(MixMasteringDto::new).collect(Collectors.toList());


    PaginationDto<MixMasteringDto> row = PaginationDto.of(new PagingDto(pageable, count), mixMasterings);
    return row;
  }
}
