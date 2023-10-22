package com.study.palette.module.mixMastering.dto.file;

import com.study.palette.module.mixMastering.entity.MixMasteringFile;

public class CreateMixMasteringFileDto {
  private String originFileName;
  private String uploadFileName;
  private int uploadfileSize;
  private String url;

  private String suffix;
  private boolean isThumbnail;

  MixMasteringFile toEntity() {
    return MixMasteringFile.builder()
            .originFileName(this.originFileName)
            .uploadFileName(this.uploadFileName)
            .uploadFileSize(this.uploadfileSize)
            .url(this.url)
            .suffix(this.suffix)
            .isThumbnail(this.isThumbnail)
            .build();
  }

}
