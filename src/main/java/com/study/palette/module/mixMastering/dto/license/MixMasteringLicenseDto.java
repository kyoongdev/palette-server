package com.study.palette.module.mixMastering.dto.license;


import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MixMasteringLicenseDto {


  @Schema(name = "아이디")
  private String id;
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
  private LocalDate createdAt;

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
