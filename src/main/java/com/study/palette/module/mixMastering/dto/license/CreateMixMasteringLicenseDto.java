package com.study.palette.module.mixMastering.dto.license;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateMixMasteringLicenseDto {

  @Schema(name = "라이센스 유형")
  private int licenseType;
  @Schema(name = "가격")
  private int price;

  @Schema(name = "제공 파일")
  private String servedType;

  @Schema(name = "수정 횟수")
  private int updateCount;

  @Schema(name = "작업 기간")
  private int period;

  @Schema(name = "시안 개수")
  private int draftCount;

  @Schema(name = "저작권 양도 여부")
  private boolean isAssign;
  @Schema(name = "성업적 사용 가능 여부")
  private boolean isUseCommercial;
  @Schema(name = "원본 파일 제공 여부")
  private boolean isServeOriginFile;

  @Schema(name = "응용 활용 여부")
  private boolean isOtherUseApproved;
}
