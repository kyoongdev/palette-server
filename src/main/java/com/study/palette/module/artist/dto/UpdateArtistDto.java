package com.study.palette.module.artist.dto;

import com.study.palette.module.artist.dto.contact.CreateArtistContactDto;
import com.study.palette.module.artist.dto.file.CreateArtistFileDto;
import com.study.palette.module.artist.dto.license.CreateArtistLicenseDto;
import java.util.List;
import lombok.Data;

@Data
public class UpdateArtistDto {

  private String serviceName;
  private String serviceInfo;

  private String editInfo;

  private int salesType;

  private boolean isSelling;

  private String userName;

  private String userEmail;

  private List<CreateArtistFileDto> artistFileDto;

  private List<CreateArtistLicenseDto> artistLicenseInfo;

  private List<CreateArtistContactDto> artistContactDto;
}
