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
import com.study.palette.module.mrBeat.entity.MrBeatMusicFIle;
import com.study.palette.module.mrBeat.entity.MrBeatRequest;
import com.study.palette.module.mrBeat.repository.MrBeatRepository;
import com.study.palette.module.mrBeat.repository.MrBeatRequestRepository;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

  public PaginationDto<MrBeatResponseDto> findMrBeat(FindMrBeatsQuery query, Pageable pageable) {

    Long count = mrBeatRepository.count();

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<MrBeatResponseDto> mrBeat = mrBeatRepository.findAll(query, pageable)
        .stream()
        .map(data -> modelMapper.map(data, MrBeatResponseDto.class))
        .collect(Collectors.toList());

    PaginationDto<MrBeatResponseDto> row = PaginationDto.of(new PagingDto(pageable, count), mrBeat);

    return row;
  }

  @Transactional
  public ResponseWithIdDto createMrBeat(CreateMrBeatDto createMrBeatDto, Users users) {

    MrBeatInfo mrBeatInfo = createMrBeatDto.toEntity(users);

    List<MrBeatLicenseInfo> mrBeatLicneses = createMrBeatDto.getMrBeatLicenseInfo().stream()
        .map(mrBeatLicense -> MrBeatLicenseInfo.from(mrBeatLicense, mrBeatInfo))
        .toList();

    List<MrBeatContact> mrBeatContacts = createMrBeatDto.getMrBeatContact().stream()
        .map(mrBeatContact -> MrBeatContact.from(mrBeatContact, mrBeatInfo))
        .toList();

    MrBeatFile mrBeatFile = createMrBeatDto.getMrBeatFile().toEntity(mrBeatInfo);

    MrBeatMusicFIle mrBeatMusicFIle = createMrBeatDto.getMrBeatMusicFile().toEntity(mrBeatInfo);

    mrBeatInfo.setMrBeatLicenseInfo(mrBeatLicneses);
    mrBeatInfo.setMrBeatContact(mrBeatContacts);
    mrBeatInfo.setMrBeatFile(mrBeatFile);
    mrBeatInfo.setMrBeatMusicFile(mrBeatMusicFIle);

    mrBeatRepository.save(mrBeatInfo);

    return ResponseWithIdDto.builder().id(mrBeatInfo.getId()).build();
  }

  @Transactional
  public ResponseWithIdDto updateMrBeat(String id, UpdateMrBeatDto updateMrBeatDto) {

    MrBeatInfo mrBeatInfo = mrBeatRepository.findById(id).orElseThrow();

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

    MrBeatMusicFIle mrBeatMusicFIle = updateMrBeatDto.getMrBeatMusicFile().toEntity(mrBeatInfo);

    mrBeatInfo.setMrBeatFile(mrBeatFile);
    mrBeatInfo.setMrBeatMusicFile(mrBeatMusicFIle);
    mrBeatRepository.save(mrBeatInfo);

    return ResponseWithIdDto.builder().id(mrBeatInfo.getId()).build();
  }

  @Transactional
  public void deleteMrBeat(String id) {
    mrBeatRepository.deleteById(id);
  }

  public void createMrBeatRequest(String id, Users users) {

    MrBeatInfo mrBeatInfo = mrBeatRepository.findById(id).orElseThrow();

    Optional<MrBeatRequest> mrBeatRequest = mrBeatRequestRepository.findByMrBeatInfoAndUserAndCreatedAt(
        mrBeatInfo, users, LocalDate.now());

    if (mrBeatRequest.isPresent()) {
      throw new IllegalArgumentException("이미 구매의뢰를 하셨습니다.");
    }

    mrBeatRequestRepository.save(MrBeatRequest.builder()
        .mrBeatInfo(mrBeatInfo)
        .users(users)
        .createAt(LocalDate.now())
        .build());

  }

  public MrBeatDetailResponseDto findMrBeatDetail(String id) {

    MrBeatInfo mrBeatInfo = mrBeatRepository.findById(id).orElse(null);

    if (mrBeatInfo == null) {
      throw new IllegalArgumentException("존재하지 않는 mr/beat 입니다.");
    }

    MrBeatDetailResponseDto mrBeatDetail = MrBeatDetailResponseDto.toEntity(mrBeatInfo);

    return mrBeatDetail;
  }
}
