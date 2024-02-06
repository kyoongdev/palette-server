package com.study.palette.module.mainHome.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopMusiciansResponseDto {
  @Schema(description = "앨범아트 랭킹")
  private List<MusicianRankDto> albumArtRanks = new ArrayList<>();
  @Schema(description = "아티스트 랭킹")
  private List<MusicianRankDto> artistRanks = new ArrayList<>();
  @Schema(description = "녹음 랭킹")
  private List<MusicianRankDto> recordingRanks = new ArrayList<>();
  @Schema(description = "MR BEAT 랭킹")
  private List<MusicianRankDto> mrBeatRanks = new ArrayList<>();
  @Schema(description = "믹스마스터링 랭킹")
  private List<MusicianRankDto> mixMasteringRanks = new ArrayList<>();
}
