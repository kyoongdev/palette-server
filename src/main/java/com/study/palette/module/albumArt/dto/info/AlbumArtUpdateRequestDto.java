package com.study.palette.module.albumArt.dto.info;

import com.study.palette.common.PaletteUtils;
import com.study.palette.module.albumArt.dto.contact.AlbumArtContactCreateDto;
import com.study.palette.module.albumArt.dto.file.AlbumArtFileCreateRequestDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoCreateRequestDto;
import com.study.palette.module.albumArt.entity.AlbumArtContact;
import com.study.palette.module.albumArt.entity.AlbumArtFile;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumArtUpdateRequestDto {

  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;
  @Schema(description = "서비스 설명", example = "테스트 서비스 설명")
  private String serviceExplain;
  @Schema(description = "수정관련 안내", example = "테스트 수정관련 안내")
  private String editInfo;
  @Schema(description = "앨범아트 파일")
  private List<AlbumArtFileCreateRequestDto> albumArtFile = new ArrayList<>();
  @Schema(description = "라이센스 정보")
  private List<AlbumArtLicenseInfoCreateRequestDto> albumArtLicenseInfo = new ArrayList<>();
  @Schema(description = "연락수단")
  private List<AlbumArtContactCreateDto> albumArtContact = new ArrayList<>();

  public AlbumArtInfo toEntity(AlbumArtInfo albumArtInfo) {
    PaletteUtils.myCopyProperties(this, albumArtInfo);

    albumArtInfo.getAlbumArtContact().clear();
    albumArtInfo.getAlbumArtLicenseInfo().clear();
    albumArtInfo.getAlbumArtFile().clear();

    albumArtInfo.getAlbumArtLicenseInfo().addAll(
        (this.getAlbumArtLicenseInfo().stream()
            .map(dto -> AlbumArtLicenseInfo.from(dto, albumArtInfo))
            .collect(Collectors.toList())));

    albumArtInfo.getAlbumArtContact().addAll(
        (this.getAlbumArtContact().stream()
            .map(dto -> AlbumArtContact.from(dto, albumArtInfo))
            .collect(Collectors.toList())));

    albumArtInfo.getAlbumArtFile().addAll(
        (this.getAlbumArtFile().stream()
            .map(dto -> AlbumArtFile.from(dto, albumArtInfo))
            .collect(Collectors.toList())));

    return albumArtInfo;
  }
}