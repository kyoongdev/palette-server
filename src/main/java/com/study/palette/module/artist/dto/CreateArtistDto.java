package com.study.palette.module.artist.dto;


import com.study.palette.module.artist.dto.artistFile.CreateArtistFileDto;
import com.study.palette.module.artist.dto.artistInfo.CreateArtistInfoDto;
import com.study.palette.module.filter.entity.FilterInfo;
import lombok.Data;

import java.util.List;

@Data
public class CreateArtistDto {
  private String serviceName;
  private String serviceInfo;

  private String editInfo;


  //필터 같은 경우는 이름만 가지고 생성이 가능해야합니다.
  //필터의 생성은 관리자에서 진행합니다.
  private FilterInfo salesType;

  private boolean serviceStatus;

  private String userEmail;

  private List<CreateArtistFileDto> artistFileDto;

  private List<CreateArtistInfoDto> artistLicenseInfo;


}
