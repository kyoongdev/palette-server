package com.study.palette.module.mixMastering.dto;

import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.user.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MixMasteringDto {
  private String id;
  private String serviceName;
  private String serviceExplain;
  private String editInfo;
  private boolean sericeStatus;
  private int genre;
  private LocalDateTime createdAt;

  private User user;


  public MixMasteringDto(MixMasteringInfo mixMasteringInfo) {
    this.id = mixMasteringInfo.getId().toString();
    this.serviceName = mixMasteringInfo.getServiceName();
    this.serviceExplain = mixMasteringInfo.getServiceExplain();
    this.editInfo = mixMasteringInfo.getEditInfo();
    this.genre = mixMasteringInfo.getGenre();
    this.sericeStatus = mixMasteringInfo.isServiceStatus();
    this.createdAt = mixMasteringInfo.getCreatedAt();
    this.user = mixMasteringInfo.getUser();
  }

}
