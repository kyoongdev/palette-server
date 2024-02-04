package com.study.palette.module.mrBeat.dto;

import com.study.palette.module.mrBeat.dto.contact.CreateMrBeatContactDto;
import com.study.palette.module.mrBeat.dto.file.CreateMrBeatFileDto;
import com.study.palette.module.mrBeat.dto.file.CreateMrBeatMusicFileDto;
import com.study.palette.module.mrBeat.dto.license.CreateMrBeatLicenseInfoDto;
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
