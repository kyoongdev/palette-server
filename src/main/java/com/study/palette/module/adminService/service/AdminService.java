package com.study.palette.module.adminService.service;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.module.adminService.dto.FindAdminServiceQuery;
import com.study.palette.module.adminService.dto.ServiceCountResponseDto;
import com.study.palette.module.adminService.dto.ServiceResponseDto;
import com.study.palette.module.adminService.repository.AdminServiceCustomRepository;
import com.study.palette.module.albumArt.repository.AlbumArtRepository;
import com.study.palette.module.artist.repository.ArtistRepository;
import com.study.palette.module.mixMastering.repository.MixMasteringRepository;
import com.study.palette.module.mrBeat.repository.MrBeatRepository;
import com.study.palette.module.recording.repository.RecordingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

  private final AlbumArtRepository albumArtRepository;
  private final ArtistRepository artistRepository;
  private final MixMasteringRepository mixMasteringRepository;
  private final RecordingRepository recordingRepository;
  private final MrBeatRepository mrBeatRepository;
  private final AdminServiceCustomRepository adminServiceCustomRepository;

  @Autowired
  public AdminService(AlbumArtRepository albumArtRepository, ArtistRepository artistRepository,
      MixMasteringRepository mixMasteringRepository, RecordingRepository recordingRepository,
      MrBeatRepository mrBeatRepository, AdminServiceCustomRepository adminServiceCustomRepository) {
    this.albumArtRepository = albumArtRepository;
    this.artistRepository = artistRepository;
    this.mixMasteringRepository = mixMasteringRepository;
    this.recordingRepository = recordingRepository;
    this.mrBeatRepository = mrBeatRepository;
    this.adminServiceCustomRepository = adminServiceCustomRepository;
  }

  // 판매글 목록 전체조회
  @Transactional(readOnly = true)
  public PaginationDto<ServiceResponseDto> getServices(FindAdminServiceQuery query,
      Pageable pageable) {
    long count = getServicesCount(query).getAllCount();

    if (count == 0) {
      return PaginationDto.of(new PagingDto(pageable, count), List.of());
    }

    List<ServiceResponseDto> allByServiceStatusAndCreatedAtDesc = adminServiceCustomRepository.findAllByServiceStatusAndCreatedAtDesc(query, pageable);

    //pageable
    return PaginationDto.of(new PagingDto(pageable, count),
        allByServiceStatusAndCreatedAtDesc);
  }

  // 판매글 목록 전체 카운트
  @Transactional(readOnly = true)
  public ServiceCountResponseDto getServicesCount(FindAdminServiceQuery query) {
    long albumArtCount = albumArtRepository.countByServiceStatus(query.isRegistrationCompleted());
    long artistCount = artistRepository.countByServiceStatus(query.isRegistrationCompleted());
    long mixMasteringCount = mixMasteringRepository.countByServiceStatus(query.isRegistrationCompleted());
    long recordingCount = recordingRepository.countByServiceStatus(query.isRegistrationCompleted());
    long mrBeatCount = mrBeatRepository.countByServiceStatus(query.isRegistrationCompleted());

    return ServiceCountResponseDto.of(
        albumArtCount + artistCount + mixMasteringCount + recordingCount + mrBeatCount,
        albumArtCount,
        mixMasteringCount,
        artistCount,
        recordingCount,
        mrBeatCount
    );
  }
}