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

  private String uploadFilePath;

  private int fileMasterCode;

  private int fileType;

  private String suffix;

  private boolean isUse;

  private LocalDate createdAt;

  public MixMasteringFileDto(MixMasteringFile mixMasteringFile) {
    this.id = mixMasteringFile.getId().toString();
    this.originFileName = mixMasteringFile.getOriginFileName();
    this.uploadFileName = mixMasteringFile.getUploadFileName();
    this.uploadfileSize = mixMasteringFile.getUploadFileSize();
    this.uploadFilePath = mixMasteringFile.getUploadFilePath();
    this.fileMasterCode = mixMasteringFile.getFileMasterCode();
    this.fileType = mixMasteringFile.getFileType();
    this.suffix = mixMasteringFile.getSuffix();
    this.isUse = mixMasteringFile.isUse();
    this.createdAt = mixMasteringFile.getCreatedAt();

  }


}
