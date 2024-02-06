package com.study.palette.module.mixMastering.service;


import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.mixMastering.dto.CreateMixMasteringDto;
import com.study.palette.module.mixMastering.dto.MixMasteringDetailDto;
import com.study.palette.module.mixMastering.dto.MixMasteringDto;
import com.study.palette.module.mixMastering.dto.MixMasteringsResponseDto;
import com.study.palette.module.mixMastering.dto.query.FindMixMasteringQuery;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringRequest;
import com.study.palette.module.mixMastering.exception.MixMasteringErrorCode;
import com.study.palette.module.mixMastering.exception.MixMasteringException;
import com.study.palette.module.mixMastering.repository.MixMasteringRepository;
import com.study.palette.module.mixMastering.repository.MixMasteringRequestRepository;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MixMasteringService {

  private final MixMasteringRepository mixMasteringRepository;
  private final MixMasteringRequestRepository mixMasteringRequestRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public MixMasteringService(MixMasteringRepository mixMasteringRepository,
      MixMasteringRequestRepository mixMasteringRequestRepository, ModelMapper modelMapper) {
    this.mixMasteringRepository = mixMasteringRepository;
    this.mixMasteringRequestRepository = mixMasteringRequestRepository;
    this.modelMapper = modelMapper;
  }

  /* MixMastering 필터 포함 조회*/
  @Transactional(readOnly = true)
  public PaginationDto<MixMasteringsResponseDto> getMixMasterings(FindMixMasteringQuery query,
      Pageable pageable) {
    Long count = mixMasteringRepository.count();

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<MixMasteringsResponseDto> mixmasterings = mixMasteringRepository.findAll(query, pageable)
        .stream()
        .map(data -> modelMapper.map(data, MixMasteringsResponseDto.class))
        .collect(Collectors.toList());

    return PaginationDto.of(new PagingDto(pageable, count), mixmasterings);
  }

  /* MixMastering 단일 조회*/
  @Transactional(readOnly = true)
  public MixMasteringDetailDto getMixMastering(String id) {
    Optional<MixMasteringInfo> mixMasteringInfo = mixMasteringRepository.findById(UUID.fromString(id));

    if (mixMasteringInfo.isEmpty()) {
      throw new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_FOUND);
    }

    return new MixMasteringDetailDto(mixMasteringInfo.get());
  }

  /* MixMastering 등록*/
  @Transactional
  public MixMasteringDto createMixMastering(
      CreateMixMasteringDto createMixMasteringDto, Users users) {
    return new MixMasteringDto(
        mixMasteringRepository.save(createMixMasteringDto.toEntity(users)));
  }

  /* MixMastering 수정*/
  @Transactional
  public void updateMixMastering(String id, MixMasteringDto mixMasteringDto,
      Users user) {
    MixMasteringInfo mixMasteringInfo = mixMasteringRepository.findById(UUID.fromString(id))
        .orElseThrow(
            () -> new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_FOUND));

    //본인이 작성한 글인지 체크
    if (!mixMasteringInfo.getUsers().getId().equals(user.getId())) {
      throw new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_YOURS);
    }

    mixMasteringRepository.save(mixMasteringDto.toEntity(mixMasteringInfo));
  }

  /* MixMastering 삭제*/
  @Transactional
  public void deleteMixMastering(String id, Users user) {
    MixMasteringInfo mixMasteringInfo = mixMasteringRepository.findById(UUID.fromString(id))
        .orElseThrow(
            () -> new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_FOUND));

    //본인이 작성한 글인지 체크
    if (!mixMasteringInfo.getUsers().getId().equals(user.getId())) {
      throw new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_YOURS);
    }

    mixMasteringRepository.delete(mixMasteringInfo);
  }

  /* MixMastering 구매의뢰*/
  @Transactional
  public void createMixMasteringRequest(String id, Users users) {
    MixMasteringInfo mixMasteringInfo = mixMasteringRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_FOUND));

    //오늘 이미 구매의뢰를 했는지 체크
    Optional<MixMasteringRequest> mixMasteringRequest = mixMasteringRequestRepository.findByMixMasteringInfoAndUsersAndCreatedAt(
        users, mixMasteringInfo, LocalDateTime.now());

    if (mixMasteringRequest.isPresent()) {
      throw new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_REQUEST_ALREADY_EXISTS);
    }

    mixMasteringRequestRepository.save(MixMasteringRequest.builder()
        .mixMasteringInfo(mixMasteringInfo)
        .users(users)
        .createdAt(LocalDateTime.now())
        .build()
    );
  }
}
