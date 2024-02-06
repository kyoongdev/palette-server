package com.study.palette.module.mainHome.service;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.mainHome.dto.TodayMusiciansResponseDto;
import com.study.palette.module.mainHome.dto.TopMusiciansResponseDto;
import com.study.palette.module.mainHome.dto.query.FindTodayMusicianQuery;
import com.study.palette.module.mainHome.dto.query.FindTopMusicianQuery;
import com.study.palette.module.mainHome.repository.MainHomeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MainHomeService {

  private final MainHomeRepository mainHomeRepository;

  public MainHomeService(MainHomeRepository mainHomeRepository) {
    this.mainHomeRepository = mainHomeRepository;
  }

  // TOP 아티스트
  public TopMusiciansResponseDto getTopArtists(FindTopMusicianQuery query) { // 2024-01-28 현재 검색조건은 없음
    return TopMusiciansResponseDto.builder()
        .albumArtRanks(mainHomeRepository.getAlbumArtRanks(query))
        .mixMasteringRanks(mainHomeRepository.getMixMasteringRanks(query))
        .artistRanks(mainHomeRepository.getArtistRanks(query))
        .mrBeatRanks(mainHomeRepository.getMrBeatRanks(query))
//        .recordingRanks(mainHomeRepository.getRecordingRanks(query)) TODO 구매의뢰하기 없음 보류
        .build();
  }

  // 오늘의 아티스트
  public PaginationDto<TodayMusiciansResponseDto> getTodayArtists(FindTodayMusicianQuery query, Pageable pageable) {
    return null;
  }


}
