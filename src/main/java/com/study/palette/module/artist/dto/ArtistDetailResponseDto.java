package com.study.palette.module.artist.dto;

import com.study.palette.module.artist.entity.ArtistContact;
import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.entity.ArtistReview;
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

  public static ArtistDetailResponseDto toEntity(ArtistInfo artistInfo) {
    return ArtistDetailResponseDto.builder()
        .id(artistInfo.getId())
        .serviceInfo(artistInfo.getServiceName())
        .editInfo(artistInfo.getEditInfo())
        .artistFile(artistInfo.getArtistFile())
        .artistContact(artistInfo.getArtistContact())
        .artistLicenseInfo(artistInfo.getArtistLicenseInfo()).build();
  }


}
