package com.study.palette.module.mixMastering.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateMixMasteringDto {

  private String serviceName;
  private String serviceExplain;
  private String editInfo;
  private boolean sericeStatus;
  private LocalDateTime createdAt;
  private String userId;
}
