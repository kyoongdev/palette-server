package com.study.palette.module.mixMastering.dto.file;

import com.study.palette.module.mixMastering.entity.MixMasteringFile;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateMixMasteringFileDto {
  private String originFileName;
  private String uploadFileName;
  private int uploadFileSize;
  private String url;

  private String suffix;
  private boolean isThumbnail;

  public MixMasteringFile toEntity(MixMasteringInfo mixMastering) {
    return MixMasteringFile.builder()
            .originFileName(this.originFileName)
            .uploadFileName(this.uploadFileName)
            .uploadFileSize(this.uploadFileSize)
            .url(this.url)
            .suffix(this.suffix)
            .isThumbnail(this.isThumbnail)
            .mixMasteringInfo(mixMastering)
            .build();
  }

}
