package com.study.palette.module.albumArt.dto.license;

import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumArtLicenseInfoWithIdDto {

  private String id;
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

  public AlbumArtLicenseInfoWithIdDto(AlbumArtLicenseInfo albumArtLicenseInfo) {
    this.id = albumArtLicenseInfo.getId().toString();
    this.licenseType = albumArtLicenseInfo.getLicenseType();
    this.price = albumArtLicenseInfo.getPrice();
    this.servedFile = albumArtLicenseInfo.getServedFile();
    this.updateCount = albumArtLicenseInfo.getUpdateCount();
    this.period = albumArtLicenseInfo.getPeriod();
    this.draftCount = albumArtLicenseInfo.getDraftCount();
    this.isAssign = albumArtLicenseInfo.isAssign();
    this.isUseCommercial = albumArtLicenseInfo.isUseCommercial();
    this.isServeOriginFile = albumArtLicenseInfo.isServeOriginFile();
    this.isOtherUseApproved = albumArtLicenseInfo.isOtherUseApproved();
  }
}
