package com.study.palette.module.adminSales.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class AdminSalesCountResponseDto {

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
