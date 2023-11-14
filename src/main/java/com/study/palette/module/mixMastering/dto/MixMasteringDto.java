package com.study.palette.module.mixMastering.dto;

import com.study.palette.common.enums.mixMastering.MixMasteringGenre;
import com.study.palette.module.mixMastering.dto.contact.MixMasteringContactDto;
import com.study.palette.module.mixMastering.dto.file.MixMasteringFileDto;
import com.study.palette.module.mixMastering.dto.license.MixMasteringLicenseDto;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.users.dto.CommonUserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class MixMasteringDto {

  @Schema(description = "아이디")
  private String id;
  @Schema(description = "서비스 제목")
  private String serviceName;

  @Schema(description = "서비스 설명")
  private String serviceExplain;

  @Schema(description = "수정 관련 안내")
  private String editInfo;

  @Schema(description = "서비스 제목")
  private boolean sericeStatus;
  @Schema(description = "작업 전 음원")
  private String beforeJobMusic;
  @Schema(description = "작업 후 음원")
  private String afterJobMusic;
  @Schema(description = "장르")
  private MixMasteringGenre genre;
  @Schema(description = "생성일자")
  private LocalDateTime createdAt;
  private CommonUserDto user;
  @Schema(description = "이미지들")
  private List<MixMasteringFileDto> mixMasteringFile;
  @Schema(description = "라이센스")
  private List<MixMasteringLicenseDto> mixMasteringLicenseInfos;
  @Schema(description = "연락수단")
  private List<MixMasteringContactDto> mixMasteringContacts;

  public MixMasteringDto(MixMasteringInfo mixMasteringInfo) {
    this.id = mixMasteringInfo.getId().toString();
    this.serviceName = mixMasteringInfo.getServiceName();
    this.serviceExplain = mixMasteringInfo.getServiceExplain();
    this.editInfo = mixMasteringInfo.getEditInfo();
    this.beforeJobMusic = mixMasteringInfo.getBeforeJobMusic();
    this.afterJobMusic = mixMasteringInfo.getAfterJobMusic();
    this.genre = MixMasteringGenre.findMixMasteringGenre(mixMasteringInfo.getGenre());
    this.sericeStatus = mixMasteringInfo.isServiceStatus();
    this.createdAt = mixMasteringInfo.getCreatedAt();
    this.user = new CommonUserDto(mixMasteringInfo.getUsers());
    this.mixMasteringFile = mixMasteringInfo.getMixMasteringFiles().stream().map(MixMasteringFileDto::new).collect(Collectors.toList());
    this.mixMasteringLicenseInfos = mixMasteringInfo.getMixMasteringLicenseInfos().stream().map(MixMasteringLicenseDto::new).collect(Collectors.toList());
    this.mixMasteringContacts = mixMasteringInfo.getMixMasteringContacts().stream().map(MixMasteringContactDto::new).collect(Collectors.toList());
  }

}
