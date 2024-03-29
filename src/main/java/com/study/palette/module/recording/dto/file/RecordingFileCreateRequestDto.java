package com.study.palette.module.recording.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordingFileCreateRequestDto {

  @Schema(description = "파일 URL", example = "https://palette-study.s3.ap-northeast-2.amazonaws.com/albumArt/2021/07/21/albumArt_20210721123456.jpg")
  String url;
  @Schema(description = "원본 파일명", example = "albumArt_20210721123456.jpg")
  String originFileName;
  @Schema(description = "업로드 파일명", example = "albumArt_20210721123456.jpg")
  String uploadFileName;
  @Schema(description = "업로드 파일 크기", example = "1000")
  int uploadFileSize;
  @Schema(description = "파일 확장자", example = "jpg")
  String suffix;
  @Schema(description = "썸네일 여부", example = "false")
  boolean isThumbnail;

}
