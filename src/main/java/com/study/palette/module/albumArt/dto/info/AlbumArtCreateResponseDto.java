package com.study.palette.module.albumArt.dto.info;

import com.study.palette.module.albumArt.dto.contact.AlbumArtContactDto;
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
  private String id;
  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;
  @Schema(description = "서비스 설명", example = "테스트 서비스 설명")
  private String serviceExplain;
  @Schema(description = "수정관련 안내", example = "테스트 수정관련 안내")
  private String editInfo;
  @Schema(description = "서비스 상태", example = "true")
  private boolean serviceStatus;
  @Schema(description = "앨범아트 생성일", example = "2021-07-21T12:34:56")
  private LocalDateTime createdAt;
  @Schema(description = "앨범아트 파일")
  private List<AlbumArtFileDto> albumArtFile;
  @Schema(description = "라이센스 정보")
  private List<AlbumArtLicenseInfoWithIdDto> albumArtLicenseInfo;
  @Schema(description = "연락수단")
  private List<AlbumArtContactDto> albumArtContactDto;


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
    this.albumArtContactDto = albumArtInfo.getAlbumArtContact().stream()
        .map(AlbumArtContactDto::new)
        .toList();
  }
}
