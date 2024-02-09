package com.study.palette.module.mainHome.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class TodayMusiciansResponseDto {

  @Schema(description = "오늘의 앨범아트")
  private List<TodayMusicianDto> todayAlbumArts = new ArrayList<>();
  @Schema(description = "오늘의 아티스트")
  private List<TodayMusicianDto> todayArtists = new ArrayList<>();
  @Schema(description = "오늘의 녹음")
  private List<TodayMusicianDto> todayRecordings = new ArrayList<>();
  @Schema(description = "MR 오늘의 BEAT")
  private List<TodayMusicianDto> todayMrBeats = new ArrayList<>();
  @Schema(description = "오늘의 믹스마스터링")
  private List<TodayMusicianDto> todayMixMasterings = new ArrayList<>();
}
