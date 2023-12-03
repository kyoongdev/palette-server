package com.study.palette.module.adminService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ServiceCountResponseDto {

  @Schema(description = "전체 판매글 수")
  private long allCount;
  @Schema(description = "albumArt 판매글 수")
  private long albumArtCount;
  @Schema(description = "mixMastering 판매글 수")
  private long mixMasteringCount;
  @Schema(description = "artist 판매글 수")
  private long artistCount;
  @Schema(description = "recording 판매글 수")
  private long recordingCount;
  @Schema(description = "mrBeat 판매글 수")
  private long mrBeatCount;
}
