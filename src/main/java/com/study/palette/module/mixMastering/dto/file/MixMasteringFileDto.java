package com.study.palette.module.mixMastering.dto.file;


import com.study.palette.module.mixMastering.entity.MixMasteringFile;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MixMasteringFileDto {
  private String id;
  private String originFileName;
  private String uploadFileName;
  private int uploadfileSize;
  private String suffix;
  private boolean isThumbnail;
  private LocalDate createdAt;

  public MixMasteringFileDto(MixMasteringFile mixMasteringFile) {
    this.id = mixMasteringFile.getId().toString();
    this.originFileName = mixMasteringFile.getOriginFileName();
    this.uploadFileName = mixMasteringFile.getUploadFileName();
    this.uploadfileSize = mixMasteringFile.getUploadFileSize();
    this.suffix = mixMasteringFile.getSuffix();
    this.isThumbnail = mixMasteringFile.isThumbnail();
    this.createdAt = mixMasteringFile.getCreatedAt();
  }
}
