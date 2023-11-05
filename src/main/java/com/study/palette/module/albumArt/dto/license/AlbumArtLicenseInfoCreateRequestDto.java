package com.study.palette.module.albumArt.dto.license;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumArtLicenseInfoCreateRequestDto {

  private int licenseType;
  private int price;
  private String servedFile;
  private int updateCount;
  private LocalDateTime period;
  private int draftCount;
  private boolean isAssign;
  private boolean isUseCommercial;
  private boolean isServeOriginFile;
  private boolean isOtherUseApproved;

  public AlbumArtLicenseInfoCreateRequestDto(AlbumArtLicenseInfoCreateRequestDto dto) {
    this.licenseType = dto.getLicenseType();
    this.price = dto.getPrice();
    this.servedFile = dto.getServedFile();
    this.updateCount = dto.getUpdateCount();
    this.period = dto.getPeriod();
    this.draftCount = dto.getDraftCount();
    this.isAssign = dto.isAssign();
    this.isUseCommercial = dto.isUseCommercial();
    this.isServeOriginFile = dto.isServeOriginFile();
    this.isOtherUseApproved = dto.isOtherUseApproved();
  }
}