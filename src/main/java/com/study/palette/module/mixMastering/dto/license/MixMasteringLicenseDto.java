package com.study.palette.module.mixMastering.dto.license;


import com.study.palette.common.enums.LicenseType;
import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MixMasteringLicenseDto {


  @Schema(description = "아이디")
  private String id;
  @Schema(description = "라이센스 유형")
  private int licenseType;
  @Schema(description = "가격")
  private int price;

  @Schema(description = "제공 파일")
  private String servedType;

  @Schema(description = "수정 횟수")
  private int updateCount;

  @Schema(description = "작업 기간")
  private int period;

  @Schema(description = "시안 개수")
  private int draftCount;

  @Schema(description = "저작권 양도 여부")
  private boolean isAssign;
  @Schema(description = "성업적 사용 가능 여부")
  private boolean isUseCommercial;
  @Schema(description = "원본 파일 제공 여부")
  private boolean isServeOriginFile;

  @Schema(description = "응용 활용 여부")
  private boolean isOtherUseApproved;
  private LocalDateTime createdAt;

  public MixMasteringLicenseDto(MixMasteringLicenseInfo mixMasteringLicenseInfo) {
    this.id = mixMasteringLicenseInfo.getId().toString();
    this.licenseType = mixMasteringLicenseInfo.getLicenseType();
    this.price = mixMasteringLicenseInfo.getPrice();
    this.servedType = mixMasteringLicenseInfo.getServedType();
    this.updateCount = mixMasteringLicenseInfo.getUpdateCount();
    this.period = mixMasteringLicenseInfo.getPeriod();
    this.draftCount = mixMasteringLicenseInfo.getDraftCount();
    this.isAssign = mixMasteringLicenseInfo.isAssign();
    this.isUseCommercial = mixMasteringLicenseInfo.isUseCommercial();
    this.isServeOriginFile = mixMasteringLicenseInfo.isServeOriginFile();
    this.isOtherUseApproved = mixMasteringLicenseInfo.isOtherUseApproved();
    this.createdAt = mixMasteringLicenseInfo.getCreatedAt();
  }


}
