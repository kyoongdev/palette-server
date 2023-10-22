package com.study.palette.module.mixMastering.dto;

import com.study.palette.module.mixMastering.dto.file.MixMasteringFileDto;
import com.study.palette.module.mixMastering.entity.MixMasteringGenre;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringReview;
import com.study.palette.module.user.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MixMasteringDetailDto {
  private String id;
  private String serviceName;
  private String serviceExplain;
  private String editInfo;
  private boolean sericeStatus;
  private LocalDateTime createdAt;
  private User user;
  private List<MixMasteringReview> reviews;
  private List<MixMasteringLicenseInfo> mixMasteringLicenseInfo;
  private List<MixMasteringFileDto> mixMasteringFile;
  private MixMasteringGenre mixMasteringGenre;

  public MixMasteringDetailDto(MixMasteringInfo mixMasteringInfo) {
    this.id = mixMasteringInfo.getId().toString();
    this.serviceName = mixMasteringInfo.getServiceName();
    this.serviceExplain = mixMasteringInfo.getServiceExplain();
    this.editInfo = mixMasteringInfo.getEditInfo();
    this.sericeStatus = mixMasteringInfo.isServiceStatus();
    this.createdAt = mixMasteringInfo.getCreatedAt();
    this.user = mixMasteringInfo.getUser();
    this.reviews = mixMasteringInfo.getMixMasteringReview();
    this.mixMasteringLicenseInfo = mixMasteringInfo.getMixMasteringLicenseInfo();
    this.mixMasteringFile = mixMasteringInfo.getMixMasteringFile().stream().map(MixMasteringFileDto::new).collect(Collectors.toList());
    this.mixMasteringGenre = mixMasteringInfo.getMixMasteringGenre();
  }
}
