package com.study.palette.module.artist.dto;

import com.study.palette.module.artist.dto.artistFile.CreateArtistFileDto;
import com.study.palette.module.artist.dto.artistInfo.CreateArtistLicenseDto;
import java.util.List;
import lombok.Data;

@Data
public class UpdateArtistDto {

  private String serviceName;
  private String serviceInfo;

  private String editInfo;

  private int salesType;

  private boolean serviceStatus;

  private String userName;

  private String userEmail;

  private List<CreateArtistFileDto> artistFileDto;

  private List<CreateArtistLicenseDto> artistLicenseInfo;

  private List<CreateArtistContactDto> artistContactDto;
}
