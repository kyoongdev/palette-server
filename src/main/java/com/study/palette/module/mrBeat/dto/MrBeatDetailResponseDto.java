package com.study.palette.module.mrBeat.dto;

import com.study.palette.common.enums.mrBeat.MrBeatMoodType;
import com.study.palette.module.mrBeat.entity.MrBeatContact;
import com.study.palette.module.mrBeat.entity.MrBeatFile;
import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import com.study.palette.module.mrBeat.entity.MrBeatLicenseInfo;
import com.study.palette.module.users.dto.CommonUserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MrBeatDetailResponseDto {

  @Schema(description = "MrBeat ID")
  private String id;

  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;

  @Schema(description = "분위기")
  private int mood;

  @Schema(description = "장르")
  private int genre;

  private MrBeatFile mrBeatFile;

  private List<MrBeatLicenseInfo> mrBeatLicenseInfo;

  private List<MrBeatContact> mrBeatContact;

  private CommonUserDto users;

  public static MrBeatDetailResponseDto toEntity(MrBeatInfo mrBeatInfo) {
    return MrBeatDetailResponseDto.builder()
        .id(mrBeatInfo.getId().toString())
        .serviceName(mrBeatInfo.getServiceName())
        .mood(mrBeatInfo.getMood().getCode())
        .genre(mrBeatInfo.getGenre().getCode())
        .mrBeatFile(mrBeatInfo.getMrBeatFile())
        .mrBeatLicenseInfo(mrBeatInfo.getMrBeatLicenseInfo())
        .mrBeatContact(mrBeatInfo.getMrBeatContact())
        .users(new CommonUserDto(mrBeatInfo.getUsers()))
        .build();
  }


}
