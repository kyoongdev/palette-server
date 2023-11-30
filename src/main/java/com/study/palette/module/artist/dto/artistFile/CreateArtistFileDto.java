package com.study.palette.module.artist.dto.artistFile;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArtistFileDto {

  @Schema(description = "파일 URL", example = "https://palette-study.s3.ap-northeast-2.amazonaws.com/artist/2021/07/21/artist_20210721123456.jpg")
  private String url;
  @Schema(description = "원본 파일명", example = "artist_20210721123456.jpg")
  private String originFileName;

  @Schema(description = "업로드 파일명", example = "artist_20210721123456.jpg")
  private String uploadFileName;

  @Schema(description = "업로드 파일 크기", example = "1000")
  private int uploadFileSize;

  @Schema(description = "파일 확장자", example = "jpg")
  private String suffix;

  @Schema(description = "썸네일 여부", example = "false")
  private boolean isThumbnail;

}
