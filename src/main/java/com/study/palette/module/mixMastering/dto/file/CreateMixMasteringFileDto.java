package com.study.palette.module.mixMastering.dto.file;

import com.study.palette.module.mixMastering.entity.MixMasteringFile;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import io.swagger.v3.oas.annotations.media.Schema;

public class CreateMixMasteringFileDto {

  @Schema(description = "원본 파일명")
  private String originFileName;
  @Schema(description = "업로드 파일명")
  private String uploadFileName;
  @Schema(description = "업로드 파일 크기")
  private int uploadFileSize;
  @Schema(description = "파일 URL")
  private String url;
  @Schema(description = "파일 확장자")
  private String suffix;
  @Schema(description = "썸네일 여부")
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
