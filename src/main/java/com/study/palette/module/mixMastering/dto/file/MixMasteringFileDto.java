package com.study.palette.module.mixMastering.dto.file;


import com.study.palette.module.mixMastering.entity.MixMasteringFile;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MixMasteringFileDto {

  @Schema(description = "파일 아이디")
  private String id;
  @Schema(description = "파일 경로")
  private String url;
  @Schema(description = "원본 파일 이름")
  private String originFileName;
  @Schema(description = "업로드 파일 이름")
  private String uploadFileName;
  @Schema(description = "업로드 파일 크기")
  private int uploadFileSize;
  @Schema(description = "파일 확장자")
  private String suffix;
  @Schema(description = "썸네일 여부")
  private boolean isThumbnail;
  @Schema(description = "파일 생성일")
  private LocalDate createdAt;


  public MixMasteringFileDto(MixMasteringFile mixMasteringFile) {
    this.id = mixMasteringFile.getId().toString();
    this.url = mixMasteringFile.getUrl();
    this.originFileName = mixMasteringFile.getOriginFileName();
    this.uploadFileName = mixMasteringFile.getUploadFileName();
    this.uploadFileSize = mixMasteringFile.getUploadFileSize();
    this.suffix = mixMasteringFile.getSuffix();
    this.isThumbnail = mixMasteringFile.isThumbnail();
    this.createdAt = mixMasteringFile.getCreatedAt();
  }
}
