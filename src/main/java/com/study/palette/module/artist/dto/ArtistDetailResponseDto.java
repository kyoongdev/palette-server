package com.study.palette.module.artist.dto;

import com.study.palette.module.artist.entity.ArtistContact;
import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.entity.ArtistReview;
import com.study.palette.module.users.dto.CommonUserDto;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistDetailResponseDto {

  private String id;

  private String serviceName;

  private String serviceInfo;

  private String editInfo;

  private List<ArtistFile> artistFile;

  private List<ArtistLicenseInfo> artistLicenseInfo;

  private List<ArtistContact> artistContact;

  private CommonUserDto commonUserDto;

  public static ArtistDetailResponseDto toEntity(ArtistInfo artistInfo) {
    return ArtistDetailResponseDto.builder()
        .id(artistInfo.getId())
        .serviceName(artistInfo.getServiceName())
        .serviceInfo(artistInfo.getServiceInfo())
        .editInfo(artistInfo.getEditInfo())
        .artistFile(artistInfo.getArtistFile())
        .artistContact(artistInfo.getArtistContact())
        .artistLicenseInfo(artistInfo.getArtistLicenseInfo())
        .commonUserDto(new CommonUserDto(artistInfo.getUsers()))
        .build();
  }


}
