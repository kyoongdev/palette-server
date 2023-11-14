package com.study.palette.module.recording.service;

import com.study.palette.common.PaletteUtils;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.recording.dto.info.RecordingCreateRequestDto;
import com.study.palette.module.recording.dto.info.RecordingCreateResponseDto;
import com.study.palette.module.recording.dto.info.RecordingDetailResponseDto;
import com.study.palette.module.recording.dto.info.RecordingResponseDto;
import com.study.palette.module.recording.dto.info.RecordingUpdateRequestDto;
import com.study.palette.module.recording.dto.query.FindRecordingQuery;
import com.study.palette.module.recording.entity.RecordingFile;
import com.study.palette.module.recording.entity.RecordingInfo;
import com.study.palette.module.recording.entity.RecordingLicenseInfo;
import com.study.palette.module.recording.exception.RecordingErrorCode;
import com.study.palette.module.recording.exception.RecordingException;
import com.study.palette.module.recording.repository.RecordingRepository;
import com.study.palette.module.users.entity.Users;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecordingService {

  private final RecordingRepository recordingRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public RecordingService(RecordingRepository recordingRepository, ModelMapper modelMapper) {
    this.recordingRepository = recordingRepository;
    this.modelMapper = modelMapper;
  }

  /* Recording 필터 포함 조회*/
  @Transactional(readOnly = true)
  public PaginationDto<RecordingResponseDto> getRecordings(FindRecordingQuery query,
      Pageable pageable) {
    Long count = recordingRepository.count();

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<RecordingResponseDto> artists = recordingRepository.findAll(query, pageable)
        .stream().map(data -> modelMapper.map(data, RecordingResponseDto.class))
        .collect(Collectors.toList());

    PaginationDto<RecordingResponseDto> row = PaginationDto.of(new PagingDto(pageable, count),
        artists);

    return row;
  }

  /* Recording 상세 조회*/
  @Transactional(readOnly = true)
  public RecordingDetailResponseDto getRecordingWithDto(String id) {
    RecordingInfo recordingInfo = recordingRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new RecordingException(RecordingErrorCode.RECORDING_NOT_FOUND));
    return new RecordingDetailResponseDto(recordingInfo);
  }

  /* Recording 등록*/
  @Transactional
  public RecordingCreateResponseDto createRecording(
      RecordingCreateRequestDto recordingCreateRequestDto, Users users) {
    return new RecordingCreateResponseDto(
        recordingRepository.save(recordingCreateRequestDto.toEntity(users)));
  }

  /* Recording 수정*/
  @Transactional
  public void updateRecording(String id, RecordingUpdateRequestDto recordingUpdateRequestDto,
      Users users) {
    RecordingInfo recordingInfo = recordingRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new RecordingException(RecordingErrorCode.RECORDING_NOT_FOUND));

    //본인이 작성한 글인지 체크
    if (!recordingInfo.getUsers().getId().equals(users.getId())) {
      throw new RecordingException(RecordingErrorCode.RECORDING_NOT_YOURS);
    }

    PaletteUtils.myCopyProperties(recordingUpdateRequestDto, recordingInfo);

    // update 전 초기화
    recordingInfo.getRecordingLicenseInfo().clear();
    recordingInfo.getRecordingFile().clear();

    recordingUpdateRequestDto.getRecordingLicenseInfo().forEach(license -> {
      recordingInfo.getRecordingLicenseInfo()
          .add(RecordingLicenseInfo.from(license, recordingInfo));
    });

    recordingUpdateRequestDto.getRecordingFiles().forEach(license -> {
      recordingInfo.getRecordingFile().add(RecordingFile.from(license, recordingInfo));
    });

    recordingRepository.save(recordingInfo);
  }

  /* Recording 삭제*/
  @Transactional
  public void deleteRecording(String id, Users users) {
    RecordingInfo recordingInfo = recordingRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new RecordingException(RecordingErrorCode.RECORDING_NOT_FOUND));

    //본인이 작성한 글인지 체크
    if (!recordingInfo.getUsers().getId().equals(users.getId())) {
      throw new RecordingException(RecordingErrorCode.RECORDING_NOT_YOURS);
    }

    recordingRepository.delete(recordingInfo);
  }
}
