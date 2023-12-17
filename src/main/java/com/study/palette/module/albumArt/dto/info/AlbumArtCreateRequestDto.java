package com.study.palette.module.albumArt.dto.info;

import com.study.palette.common.enums.albumArt.AlbumArtSaleType;
import com.study.palette.module.albumArt.dto.contact.AlbumArtContactCreateDto;
import com.study.palette.module.albumArt.dto.file.AlbumArtFileCreateRequestDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoCreateRequestDto;
import com.study.palette.module.albumArt.entity.AlbumArtContact;
import com.study.palette.module.albumArt.entity.AlbumArtFile;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.users.entity.Users;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumArtCreateRequestDto {

  @NotBlank(message = "서비스명을 입력해주세요.")
  public String serviceName;
  @NotBlank(message = "서비스 설명을 입력해주세요.")
  public String serviceExplain;
  public int salesType;
  @NotBlank(message = "수정관련 안내를 입력해주세요.")
  public String editInfo;
  @NotNull(message = "라이센스 정보를 입력해주세요.")
  public List<AlbumArtLicenseInfoCreateRequestDto> albumArtLicenseInfo = new ArrayList<>();
  @NotNull(message = "연락수단을 하나이상 기재해 주세요.")
  public List<AlbumArtContactCreateDto> albumArtContact = new ArrayList<>();
  public List<AlbumArtFileCreateRequestDto> albumArtFiles = new ArrayList<>();

  public boolean serviceStatus;

  public AlbumArtInfo toEntity(Users users) {
    AlbumArtInfo albumArtInfo = AlbumArtInfo.builder()
        .serviceName(this.getServiceName())
        .serviceExplain(this.getServiceExplain())
        .serviceStatus(this.isServiceStatus())
        .salesType(AlbumArtSaleType.findAlbumArtSaleType(this.getSalesType()))
        .editInfo(this.getEditInfo())
        .users(users)
        .build();

    albumArtInfo.setAlbumArtLicenseInfo(this.getAlbumArtLicenseInfo().stream()
        .map(dto -> AlbumArtLicenseInfo.from(dto, albumArtInfo))
        .toList());

    albumArtInfo.setAlbumArtContact(this.getAlbumArtContact().stream()
        .map(dto -> AlbumArtContact.from(dto, albumArtInfo))
        .toList());

    albumArtInfo.setAlbumArtFiles(this.getAlbumArtFiles().stream()
        .map(dto -> AlbumArtFile.from(dto, albumArtInfo))
        .toList());

    return albumArtInfo;
  }
}