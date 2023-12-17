package com.study.palette.module.mixMastering.dto;


import com.study.palette.module.mixMastering.dto.contact.CreateMixMasteringContactDto;
import com.study.palette.module.mixMastering.dto.file.CreateMixMasteringFileDto;
import com.study.palette.module.mixMastering.dto.license.CreateMixMasteringLicenseDto;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMixMasteringDto {

  @Schema(description = "서비스 제목")
  private String serviceName;
  @Schema(description = "서비스 설명")
  private String serviceExplain;
  @Schema(description = "수정 관련 안내")
  private String editInfo;
  @Schema(description = "서비스 상태")
  private boolean sericeStatus;
  @Schema(description = "작업 전 음원")
  private String beforeJobMusic;
  @Schema(description = "작업 후 음원")
  private String afterJobMusic;
  @Schema(description = "장르")
  private int genre;
  @Schema(description = "라이센스 정보")
  private List<CreateMixMasteringLicenseDto> licenses;
  @Schema(description = "이미지들")
  private List<CreateMixMasteringFileDto> mixMasteringFiles;
  @Schema(description = "SNS 주소들")
  private List<CreateMixMasteringContactDto> contacts;

  public MixMasteringInfo toEntity(Users user) {
    MixMasteringInfo mixMastering = MixMasteringInfo.builder()
        .serviceName(this.serviceName)
        .beforeJobMusic(this.beforeJobMusic)
        .afterJobMusic(this.afterJobMusic)
        .serviceExplain(this.serviceExplain)
        .editInfo(this.editInfo)
        .serviceStatus(this.sericeStatus)
        .genre(this.genre)
        .users(user)
        .build();
    mixMastering.setMixMasteringLicenseInfos(this.licenses.stream().map(license -> license.toEntity(mixMastering)).toList());
    mixMastering.setMixMasteringFiles(this.mixMasteringFiles.stream().map(file -> file.toEntity(mixMastering)).toList());
    mixMastering.setMixMasteringContacts(this.contacts.stream().map(contact -> contact.toEntity(mixMastering)).toList());

    return mixMastering;
  }
}
