package com.study.palette.module.mixMastering.dto.file;


import lombok.Data;

import java.time.LocalDate;

@Data
public class MixMasteringFileDto {
  private String id;
  private String originFileName;
  private String uploadFileName;

  private int uploadfileSize;

  private String uploadFilePath;

  private int fileMasterCode;

  private int fileType;

  private String suffic;

  private boolean isUse;

  private LocalDate createdAt;


}
