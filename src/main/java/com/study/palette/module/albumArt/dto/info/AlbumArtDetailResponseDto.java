package com.study.palette.module.albumArt.dto.info;

import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoWithIdDto;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlbumArtDetailResponseDto {

  private String id;
  private String serviceName;
  private String serviceExplain;
  private String editInfo;
  private boolean serviceStatus;
  private LocalDateTime createdAt;
  //    private List<AlbumArtFileResponseDto> albumArtFileResponseDto;
  private List<AlbumArtLicenseInfoWithIdDto> licenseInfos;


  public AlbumArtDetailResponseDto(AlbumArtInfo albumArtInfo,
//                                     List<lbumArtFileResponseDto> albumArtFileResponseDto,
      List<AlbumArtLicenseInfoWithIdDto> albumArtLicenseInfoResponseDto) {
    this.id = albumArtInfo.getId().toString();
    this.serviceName = albumArtInfo.getServiceName();
    this.serviceExplain = albumArtInfo.getServiceExplain();
    this.editInfo = albumArtInfo.getEditInfo();
    this.serviceStatus = albumArtInfo.isServiceStatus();
    this.createdAt = albumArtInfo.getCreatedAt();
    this.licenseInfos = albumArtLicenseInfoResponseDto;
//        this.albumArtFileResponseDto = albumArtFileResponseDto;
  }
}
