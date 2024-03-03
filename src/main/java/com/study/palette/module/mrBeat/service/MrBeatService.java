package com.study.palette.module.mrBeat.service;

import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.mrBeat.dto.CreateMrBeatDto;
import com.study.palette.module.mrBeat.dto.FindMrBeatsQuery;
import com.study.palette.module.mrBeat.dto.MrBeatDetailResponseDto;
import com.study.palette.module.mrBeat.dto.MrBeatResponseDto;
import com.study.palette.module.mrBeat.dto.UpdateMrBeatDto;
import com.study.palette.module.mrBeat.entity.MrBeatContact;
import com.study.palette.module.mrBeat.entity.MrBeatFile;
import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import com.study.palette.module.mrBeat.entity.MrBeatLicenseInfo;
import com.study.palette.module.mrBeat.entity.MrBeatMusicFile;
import com.study.palette.module.mrBeat.entity.MrBeatRequest;
import com.study.palette.module.mrBeat.exception.MrBeatErrorCode;
import com.study.palette.module.mrBeat.exception.MrBeatException;
import com.study.palette.module.mrBeat.repository.MrBeatRepository;
import com.study.palette.module.mrBeat.repository.MrBeatRequestRepository;
import com.study.palette.module.musician.dto.ApprovingServiceDetailResponseDto;
import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MrBeatService {

  private final MrBeatRepository mrBeatRepository;

  private final MrBeatRequestRepository mrBeatRequestRepository;

  private ModelMapper modelMapper;

  @Autowired
  public MrBeatService(MrBeatRepository mrBeatRepository,
      MrBeatRequestRepository mrBeatRequestRepository, ModelMapper modelMapper) {
    this.mrBeatRepository = mrBeatRepository;
    this.mrBeatRequestRepository = mrBeatRequestRepository;
    this.modelMapper = modelMapper;
  }

  public PaginationDto<MrBeatResponseDto> findMrBeat(MrBeatConditions query, Pageable pageable) {

    Long count = mrBeatRepository.count();

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<MrBeatResponseDto> mrBeat = mrBeatRepository.findAll(query, pageable).stream()
        .map(data -> modelMapper.map(data, MrBeatResponseDto.class)).collect(Collectors.toList());

    PaginationDto<MrBeatResponseDto> row = PaginationDto.of(new PagingDto(pageable, count), mrBeat);

    return row;
  }

  public MrBeatDetailResponseDto findMrBeatDetail(String id) {

    MrBeatInfo mrBeatInfo = mrBeatRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new MrBeatException(MrBeatErrorCode.MRBEAT_NOT_FOUND));

    MrBeatDetailResponseDto mrBeatDetail = MrBeatDetailResponseDto.toEntity(mrBeatInfo);

    return mrBeatDetail;
  }

  @Transactional
  public ResponseWithIdDto createMrBeat(CreateMrBeatDto createMrBeatDto, Users users) {

    MrBeatInfo mrBeatInfo = createMrBeatDto.toEntity(users);

    List<MrBeatLicenseInfo> mrBeatLicneses = createMrBeatDto.getMrBeatLicenseInfo().stream()
        .map(mrBeatLicense -> MrBeatLicenseInfo.from(mrBeatLicense, mrBeatInfo)).toList();

    List<MrBeatContact> mrBeatContacts = createMrBeatDto.getMrBeatContact().stream()
        .map(mrBeatContact -> MrBeatContact.from(mrBeatContact, mrBeatInfo)).toList();

    MrBeatFile mrBeatFile = createMrBeatDto.getMrBeatFile().toEntity(mrBeatInfo);

    MrBeatMusicFile mrBeatMusicFIle = createMrBeatDto.getMrBeatMusicFile().toEntity(mrBeatInfo);

    mrBeatInfo.setMrBeatLicenseInfo(mrBeatLicneses);
    mrBeatInfo.setMrBeatContact(mrBeatContacts);
    mrBeatInfo.setMrBeatFile(mrBeatFile);
    mrBeatInfo.setMrBeatMusicFile(mrBeatMusicFIle);

    mrBeatRepository.save(mrBeatInfo);

    return ResponseWithIdDto.builder().id(mrBeatInfo.getId().toString()).build();
  }

  @Transactional
  public ResponseWithIdDto updateMrBeat(String id, UpdateMrBeatDto updateMrBeatDto, Users users) {

    MrBeatInfo mrBeatInfo = mrBeatRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new MrBeatException(MrBeatErrorCode.MRBEAT_NOT_FOUND));

    if (users.getRole() == Role.MUSICIAN && !mrBeatInfo.getUsers().getId().equals(users.getId())) {
      throw new MrBeatException(MrBeatErrorCode.MRBEAT_NOT_YOURS);
    }

    PaletteUtils.myCopyProperties(updateMrBeatDto, mrBeatInfo);

    mrBeatInfo.getMrBeatLicenseInfo().clear();
    mrBeatInfo.getMrBeatContact().clear();

    updateMrBeatDto.getMrBeatLicenseInfo().stream().forEach(mrBeatLicneses -> {
      mrBeatInfo.getMrBeatLicenseInfo().add(MrBeatLicenseInfo.from(mrBeatLicneses, mrBeatInfo));
    });

    updateMrBeatDto.getMrBeatContact().stream().forEach(mrBeatContact -> {
      mrBeatInfo.getMrBeatContact().add(MrBeatContact.from(mrBeatContact, mrBeatInfo));
    });

    MrBeatFile mrBeatFile = updateMrBeatDto.getMrBeatFile().toEntity(mrBeatInfo);

    MrBeatMusicFile mrBeatMusicFIle = updateMrBeatDto.getMrBeatMusicFile().toEntity(mrBeatInfo);

    mrBeatInfo.setMrBeatFile(mrBeatFile);
    mrBeatInfo.setMrBeatMusicFile(mrBeatMusicFIle);
    mrBeatRepository.save(mrBeatInfo);

    return ResponseWithIdDto.builder().id(mrBeatInfo.getId().toString()).build();
  }

  @Transactional
  public void deleteMrBeat(String id, Users users) {

    MrBeatInfo mrBeatInfo = mrBeatRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new MrBeatException(MrBeatErrorCode.MRBEAT_NOT_FOUND));

    if (users.getRole() == Role.MUSICIAN && !mrBeatInfo.getUsers().getId().equals(users.getId())) {
      throw new MrBeatException(MrBeatErrorCode.MRBEAT_NOT_YOURS);
    }

    mrBeatRepository.deleteById(UUID.fromString(id));
  }

  @Transactional
  public void createMrBeatRequest(String id, Users users) {

    MrBeatInfo mrBeatInfo = mrBeatRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new MrBeatException(MrBeatErrorCode.MRBEAT_NOT_FOUND));

    Optional<MrBeatRequest> mrBeatRequest = mrBeatRequestRepository.findByMrBeatInfoAndUserAndCreatedAt(
        mrBeatInfo, users, LocalDateTime.now());

    if (mrBeatRequest.isPresent()) {
      throw new MrBeatException(MrBeatErrorCode.MRBEAT_REQUEST_ALREADY_EXISTS);
    }

    mrBeatRequestRepository.save(
        MrBeatRequest.builder().mrBeatInfo(mrBeatInfo).users(users).createdAt(LocalDateTime.now())
            .build());

  }

  /* 뮤지션 판매 일시중지 판매재개 */
  @Transactional
  @PreAuthorize("hasRole('MUSICIAN') or hasRole('ADMIN')")
  public void updateIsSelling(String id, boolean status) {

    MrBeatInfo mrBeatInfo = mrBeatRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new MrBeatException(MrBeatErrorCode.MRBEAT_NOT_FOUND));

    mrBeatInfo.updateIsSelling(status);
    mrBeatRepository.save(mrBeatInfo);
  }


  public ApprovingServiceDetailResponseDto approvingServiceDetail(String id) {

    MrBeatInfo mrBeatInfo = mrBeatRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new MrBeatException(MrBeatErrorCode.MRBEAT_NOT_FOUND));

    return new ApprovingServiceDetailResponseDto(mrBeatInfo);
  }
}
