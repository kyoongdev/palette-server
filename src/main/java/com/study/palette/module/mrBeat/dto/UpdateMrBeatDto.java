package com.study.palette.module.mrBeat.dto;

import java.util.List;
import lombok.Data;

@Data
public class UpdateMrBeatDto {

  private String serviceName;
  private int salesType;
  private int genre;
  private int mood;
  private List<CreateMrBeatLicenseInfoDto> mrBeatLicenseInfo;
  private List<CreateMrBeatContactDto> mrBeatContact;
  private CreateMrBeatFileDto mrBeatFile;
  private CreateMrBeatMusicFileDto mrBeatMusicFile;

}
