package com.study.palette.module.artist.dto;


import com.study.palette.module.artist.dto.artistFile.CreateArtistFileDto;
import com.study.palette.module.artist.dto.artistInfo.CreateArtistInfoDto;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArtistDto {

  private String serviceName;
  private String serviceInfo;

  private String editInfo;


  //필터 같은 경우는 이름만 가지고 생성이 가능해야합니다.
  //필터의 생성은 관리자에서 진행합니다.
  private int salesType;

  private boolean serviceStatus;

  private LocalDate createdAt;

  private List<CreateArtistFileDto> artistFileDto;

  private List<CreateArtistInfoDto> artistLicenseInfo;

  private List<CreateArtistContactDto> artistContactDto;

  public ArtistInfo toEntity(Users users) {
    return ArtistInfo.builder()
        .serviceName(this.getServiceName())
        .salesType(this.getSalesType())
        .serviceInfo(this.getServiceInfo())
        .serviceStatus(this.isServiceStatus())
        .createdAt(this.getCreatedAt())
        .artistFile(new ArrayList<>())
        .artistReview(new ArrayList<>())
        .artistContact(new ArrayList<>())
        .artistLicenseInfo(new ArrayList<>())
        .users(users).build();
  }


}
