package com.study.palette.module.mixMastering.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateMixMasteringDto {

  @Schema(name = "서비스 제목")
  private String serviceName;
  @Schema(name = "서비스 설명")
  private String serviceExplain;
  @Schema(name = "수정 관련 안내")
  private String editInfo;
  @Schema(name = "서비스 상태")
  private boolean sericeStatus;
  @Schema(name = "작업 전 음원")
  private String beforeJobMusic;
  @Schema(name = "작업 후 음원")
  private String afterJobMusic;
  @Schema(name = "장르")
  private int genre;
}
