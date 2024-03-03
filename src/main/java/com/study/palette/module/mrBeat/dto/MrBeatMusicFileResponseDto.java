package com.study.palette.module.mrBeat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MrBeatMusicFileResponseDto {

  @Schema(description = "음악파일경로", example = "https://pallete-file.s3.ap-northeast-2.amazonaws.com/7c540940-8dcb-43e7-8a58-91c6066c860d.mp3")
  private String musicFileUrl;

  @Schema(description = "분", example = "1")
  private int minutes;

  @Schema(description = "초", example = "1")
  private int seconds;

  public MrBeatMusicFileResponseDto(String musicFileUrl, int minutes, int seconds) {
    this.musicFileUrl = musicFileUrl;
    this.minutes = minutes / 60;
    this.seconds = seconds % 60;
  }

}
