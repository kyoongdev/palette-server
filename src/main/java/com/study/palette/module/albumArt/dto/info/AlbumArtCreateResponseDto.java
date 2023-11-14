package com.study.palette.module.albumArt.dto.info;

import com.study.palette.module.albumArt.dto.file.AlbumArtFileDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoWithIdDto;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class AlbumArtCreateResponseDto {

  @Schema(description = "앨범아트 ID")
  public String id;
  @Schema(description = "서비스명", example = "테스트 서비스명")
  public String serviceName;
  @Schema(description = "서비스 설명", example = "테스트 서비스 설명")
  public String serviceExplain;
  @Schema(description = "수정관련 안내", example = "테스트 수정관련 안내")
  public String editInfo;
  @Schema(description = "서비스 상태", example = "true")
  public boolean serviceStatus;
  @Schema(description = "앨범아트 생성일", example = "2021-07-21T12:34:56")
  public LocalDateTime createdAt;
  @Schema(description = "앨범아트 파일")
  public List<AlbumArtFileDto> albumArtFile;
  @Schema(description = "라이센스 정보")
  public List<AlbumArtLicenseInfoWithIdDto> albumArtLicenseInfo;

  public AlbumArtCreateResponseDto(AlbumArtInfo albumArtInfo) {
    this.id = albumArtInfo.getId().toString();
    this.serviceName = albumArtInfo.getServiceName();
    this.serviceExplain = albumArtInfo.getServiceExplain();
    this.editInfo = albumArtInfo.getEditInfo();
    this.serviceStatus = albumArtInfo.isServiceStatus();
    this.createdAt = albumArtInfo.getCreatedAt();
    this.albumArtFile = albumArtInfo.getAlbumArtFile().stream()
        .map(AlbumArtFileDto::new)
        .toList();
    this.albumArtLicenseInfo = albumArtInfo.getAlbumArtLicenseInfo().stream()
        .map(AlbumArtLicenseInfoWithIdDto::new)
        .toList();
  }
}
