package com.study.palette.module.musician.dto;

import com.study.palette.module.musician.entity.UsersMusician;
import com.study.palette.module.musician.entity.UsersMusicianFile;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMusicianFileDto {
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

  @Schema(description = "사용 여부", example = "true")
  private boolean isUse;

  public UsersMusicianFile toEntity(UsersMusician usersMusician) {
    return UsersMusicianFile.builder()
        .url(this.getUrl())
        .originFileName(this.getOriginFileName())
        .uploadFileName(this.getUploadFileName())
        .uploadFileSize(this.getUploadFileSize())
        .suffix(this.getSuffix())
        .isUse(this.isUse())
        .usersMusician(usersMusician)
        .createdAt(LocalDateTime.now())
        .build();
  }

}
