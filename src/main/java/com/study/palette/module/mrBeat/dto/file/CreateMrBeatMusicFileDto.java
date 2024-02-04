package com.study.palette.module.mrBeat.dto.file;

import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import com.study.palette.module.mrBeat.entity.MrBeatMusicFile;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMrBeatMusicFileDto {

  @Schema(description = "파일 URL", example = "https://palette-study.s3.ap-northeast-2.amazonaws.com/mrBeat/2021/07/21/mrBeat_20210721123456.jpg")
  private String url;

  @Schema(description = "원본 파일명", example = "mrBeat_20210721123456.jpg")
  private String originFileName;

  @Schema(description = "업로드 파일명", example = "mrBeat_20210721123456.jpg")
  private String uploadFileName;

  @Schema(description = "업로드 파일 크기", example = "1000")
  private int uploadFileSize;

  @Schema(description = "파일 확장자", example = "jpg")
  private String suffix;

  private boolean isUse;

  private LocalDateTime createdAt;

  public MrBeatMusicFile toEntity(MrBeatInfo mrBeatInfo) {
    return MrBeatMusicFile.builder()
        .url(this.getUrl())
        .originFileName(this.getOriginFileName())
        .uploadFileName(this.getUploadFileName())
        .uploadFileSize(this.getUploadFileSize())
        .suffix(this.getSuffix())
        .isUse(this.isUse())
        .createdAt(LocalDateTime.now())
        .mrBeatInfo(mrBeatInfo)
        .users(mrBeatInfo.getUsers())
        .build();
  }
}
