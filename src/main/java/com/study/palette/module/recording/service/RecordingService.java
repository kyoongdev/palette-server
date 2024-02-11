package com.study.palette.module.recording.service;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.recording.dto.info.RecordingCreateRequestDto;
import com.study.palette.module.recording.dto.info.RecordingCreateResponseDto;
import com.study.palette.module.recording.dto.info.RecordingDetailResponseDto;
import com.study.palette.module.recording.dto.info.RecordingResponseDto;
import com.study.palette.module.recording.dto.info.RecordingUpdateRequestDto;
import com.study.palette.module.recording.entity.RecordingInfo;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
  public PaginationDto<RecordingResponseDto> getRecordings(RecordingConditions query,
      Pageable pageable) {
    Long count = recordingRepository.count();

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<RecordingResponseDto> recordings = recordingRepository.findAll(query, pageable)
        .stream().map(data -> modelMapper.map(data, RecordingResponseDto.class))
        .collect(Collectors.toList());

    return PaginationDto.of(new PagingDto(pageable, count), recordings);
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
  @PreAuthorize("hasRole('MUSICIAN')")
  public RecordingCreateResponseDto createRecording(
      RecordingCreateRequestDto recordingCreateRequestDto, Users users) {
    return new RecordingCreateResponseDto(
        recordingRepository.save(recordingCreateRequestDto.toEntity(users)));
  }

  /* Recording 수정*/
  @Transactional
  @PreAuthorize("hasRole('MUSICIAN') or hasRole('ADMIN')")
  public void updateRecording(String id, RecordingUpdateRequestDto recordingUpdateRequestDto,
      Users users) {
    RecordingInfo recordingInfo = recordingRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new RecordingException(RecordingErrorCode.RECORDING_NOT_FOUND));

    //본인이 작성한 글인지 체크
    if (!recordingInfo.getUsers().getId().equals(users.getId())) {
      throw new RecordingException(RecordingErrorCode.RECORDING_NOT_YOURS);
    }

    recordingRepository.save(recordingUpdateRequestDto.toEntity(recordingInfo));
  }

  /* Recording 삭제*/
  @Transactional
  @PreAuthorize("hasRole('MUSICIAN') or hasRole('ADMIN')")
  public void deleteRecording(String id, Users users) {
    RecordingInfo recordingInfo = recordingRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new RecordingException(RecordingErrorCode.RECORDING_NOT_FOUND));

    //본인이 작성한 글인지 체크
    if (!recordingInfo.getUsers().getId().equals(users.getId())) {
      throw new RecordingException(RecordingErrorCode.RECORDING_NOT_YOURS);
    }

    recordingRepository.delete(recordingInfo);
  }

  /* recording 판매글 등록/신청 승인 반려 처리*/
  @Transactional
  @PreAuthorize("hasRole('MUSICIAN') or hasRole('ADMIN')")
  public void updateIsSelling(String id, boolean status) {
    RecordingInfo recordingInfo = recordingRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new RecordingException(RecordingErrorCode.RECORDING_NOT_FOUND));

    recordingInfo.updateIsSelling(status);

    recordingRepository.save(recordingInfo);
  }
}
