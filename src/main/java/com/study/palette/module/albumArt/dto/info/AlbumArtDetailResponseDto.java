package com.study.palette.module.albumArt.dto.info;

import com.study.palette.common.enums.albumArt.AlbumArtSaleType;
import com.study.palette.module.albumArt.dto.contact.AlbumArtContactDto;
import com.study.palette.module.albumArt.dto.file.AlbumArtFileDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoWithIdDto;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.users.dto.CommonUserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlbumArtDetailResponseDto {

  @Schema(description = "앨범아트 ID")
  private String id;
  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;
  @Schema(description = "서비스 설명", example = "테스트 서비스 설명")
  private String serviceExplain;
  @Schema(description = "작업분야")
  private int salesType;
  @Schema(description = "수정관련 안내", example = "테스트 수정관련 안내")
  private String editInfo;
  @Schema(description = "앨범아트 생성일", example = "2021-07-21T12:34:56")
  private LocalDateTime createdAt;
  @Schema(description = "앨범아트 파일")
  private List<AlbumArtFileDto> albumArtFileDto;
  @Schema(description = "연락수단")
  private List<AlbumArtContactDto> albumArtContactDto;
  @Schema(description = "라이센스 정보")
  private List<AlbumArtLicenseInfoWithIdDto> albumArtLicenseInfoResponseDto;

  @Schema(description = "유저 정보")
  private CommonUserDto users;

  public AlbumArtDetailResponseDto(AlbumArtInfo albumArtInfo) {
    this.id = albumArtInfo.getId().toString();
    this.serviceName = albumArtInfo.getServiceName();
    this.serviceExplain = albumArtInfo.getServiceExplain();
    this.editInfo = albumArtInfo.getEditInfo();
    this.salesType = albumArtInfo.getSalesType().getCode();
    this.createdAt = albumArtInfo.getCreatedAt();
    this.albumArtLicenseInfoResponseDto = albumArtInfo.getAlbumArtLicenseInfo().stream()
        .map(AlbumArtLicenseInfoWithIdDto::new).toList();
    this.albumArtContactDto = albumArtInfo.getAlbumArtContact().stream()
        .map(AlbumArtContactDto::new).toList();
    this.albumArtFileDto = albumArtInfo.getAlbumArtFile().stream()
        .map(AlbumArtFileDto::new).toList();
    this.users = new CommonUserDto(albumArtInfo.getUsers());
  }
}
