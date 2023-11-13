package com.study.palette.module.mixMastering.service;


import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.mixMastering.dto.CreateMixMasteringDto;
import com.study.palette.module.mixMastering.dto.MixMasteringDetailDto;
import com.study.palette.module.mixMastering.dto.MixMasteringDto;
import com.study.palette.module.mixMastering.dto.query.FindMixMasteringQuery;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import com.study.palette.module.mixMastering.exception.MixMasteringErrorCode;
import com.study.palette.module.mixMastering.exception.MixMasteringException;
import com.study.palette.module.mixMastering.repository.MixMasteringRepository;
import com.study.palette.module.user.entity.User;
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
  private final ModelMapper modelMapper;

  @Autowired
  public MixMasteringService(MixMasteringRepository mixMasteringRepository,
      ModelMapper modelMapper) {
    this.mixMasteringRepository = mixMasteringRepository;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public PaginationDto<MixMasteringDto> getMixMasterings(Pageable pageable) {
    Long count = mixMasteringRepository.count();
    List<MixMasteringDto> mixMasterings = mixMasteringRepository.findAll(pageable).stream()
        .map(MixMasteringDto::new).collect(Collectors.toList());

    PaginationDto<MixMasteringDto> row = PaginationDto.of(new PagingDto(pageable, count),
        mixMasterings);
    return row;
  }

  /* MixMastering 필터 포함 조회*/
  @Transactional(readOnly = true)
  public PaginationDto<MixMasteringDto> getixmMasterings(FindMixMasteringQuery query,
      Pageable pageable) {
    Long count = mixMasteringRepository.count();

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<MixMasteringDto> artists = mixMasteringRepository.findAll(query, pageable)
        .stream()
        .map(data -> modelMapper.map(data, MixMasteringDto.class))
        .collect(Collectors.toList());

    PaginationDto<MixMasteringDto> row = PaginationDto.of(new PagingDto(pageable, count),
        artists);

    return row;
  }

  @Transactional(readOnly = true)
  public MixMasteringDetailDto getMixMastering(String id) {
    Optional<MixMasteringInfo> mixMasteringInfo = mixMasteringRepository.findById(
        UUID.fromString(id));
    if (mixMasteringInfo.isEmpty()) {
      throw new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_FOUND);
    }
    log.info("*MixMastering* : " + mixMasteringInfo.get().getMixMasteringLicenseInfos());

    return new MixMasteringDetailDto(mixMasteringInfo.get());
  }

  /* MixMastering 등록*/
  @Transactional
  public MixMasteringDto createMixMastering(
      CreateMixMasteringDto createMixMasteringDto, User user) {
    return new MixMasteringDto(
        mixMasteringRepository.save(createMixMasteringDto.toEntity(user)));
  }

  /* MixMastering 수정*/
  @Transactional
  public void updateMixMastering(MixMasteringDto mixMasteringUpdateRequestDto,
      User user) {
    MixMasteringInfo mixMasteringInfo = mixMasteringRepository.findById(
            UUID.fromString(mixMasteringUpdateRequestDto.getId()))
        .orElseThrow(
            () -> new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_FOUND));

    //본인이 작성한 글인지 체크
    if (!mixMasteringInfo.getUser().getId().equals(user.getId())) {
      throw new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_YOURS);
    }

    PaletteUtils.myCopyProperties(mixMasteringUpdateRequestDto, mixMasteringInfo);

    // update 전 초기화
    mixMasteringInfo.getMixMasteringLicenseInfos().clear();
//        mixMasteringInfo.getMixMasteringFile().clear(); TODO 파일 구현 후 추가

    mixMasteringUpdateRequestDto.getMixMasteringLicenseInfos().stream().forEach(license -> {
      mixMasteringInfo.getMixMasteringLicenseInfos()
          .add(MixMasteringLicenseInfo.from(license, mixMasteringInfo));
    });

    //TODO 파일 구현 후 추가
//        mixMasteringUpdateReqeustDto.getMixMasteringLicenseInfo().stream().forEach(license -> {
//            mixMasteringInfo.getMixMasteringLicenseInfo().add(MixMasteringLicenseInfo.from(license, mixMasteringInfo));
//        });

    mixMasteringRepository.save(mixMasteringInfo);
  }

  /* MixMastering 삭제*/
  @Transactional
  public void deleteMixMastering(String id, User user) {
    MixMasteringInfo mixMasteringInfo = mixMasteringRepository.findById(UUID.fromString(id))
        .orElseThrow(
            () -> new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_FOUND));

    //본인이 작성한 글인지 체크
    if (!mixMasteringInfo.getUser().getId().equals(user.getId())) {
      throw new MixMasteringException(MixMasteringErrorCode.MIX_MASTERING_NOT_YOURS);
    }

    mixMasteringRepository.delete(mixMasteringInfo);
  }
}
