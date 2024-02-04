package com.study.palette.module.mrBeat.dto;

import com.study.palette.common.enums.mrBeat.MrBeatGenreType;
import com.study.palette.common.enums.mrBeat.MrBeatMoodType;
import com.study.palette.common.enums.mrBeat.MrBeatSalesType;
import com.study.palette.module.mrBeat.dto.contact.CreateMrBeatContactDto;
import com.study.palette.module.mrBeat.dto.file.CreateMrBeatFileDto;
import com.study.palette.module.mrBeat.dto.file.CreateMrBeatMusicFileDto;
import com.study.palette.module.mrBeat.dto.license.CreateMrBeatLicenseInfoDto;
import com.study.palette.module.mrBeat.entity.MrBeatFile;
import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
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
public class CreateMrBeatDto {

  @NotBlank(message = "서비스명을 입력해주세요.")
  private String serviceName;

  private int salesType;

  private int genre;

  private int mood;

  @NotNull(message = "라이센스 정보를 입력해주세요.")
  private List<CreateMrBeatLicenseInfoDto> mrBeatLicenseInfo;

  @NotNull(message = "연락수단을 하나이상 기재해 주세요.")
  private List<CreateMrBeatContactDto> mrBeatContact;

  @NotNull(message = "썸네일을 등록해주세요.")
  private CreateMrBeatFileDto mrBeatFile;

  @NotNull(message = "음원을 등록해주세요.")
  private CreateMrBeatMusicFileDto mrBeatMusicFile;

  public boolean serviceStatus;

  public MrBeatInfo toEntity(Users users) {
    return MrBeatInfo.builder()
        .serviceName(this.getServiceName())
        .salesType(MrBeatSalesType.findMrBeatSalesType(this.getSalesType()))
        .genre(MrBeatGenreType.findMrBeatGenreType(this.getGenre()))
        .mood(MrBeatMoodType.findMrBeatMoodType(this.getMood()))
        .serviceStatus(this.isServiceStatus())
        .createdAt(LocalDateTime.now())
        .mrBeatFile(new MrBeatFile())
        .mrBeatContact(new ArrayList<>())
        .users(users)
        .mrBeatLicenseInfo(new ArrayList<>()).build();
  }

}
