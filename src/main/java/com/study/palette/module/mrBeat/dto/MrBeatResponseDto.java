package com.study.palette.module.mrBeat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MrBeatResponseDto {

  @Schema(description = "MrBeat ID")
  private String id;
  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;
  @Schema(description = "장르", example = "true")
  private int genre;
  @Schema(description = "분위기", example = "true")
  private int mood;
  @Schema(description = "서비스 타입", example = "true")
  private int salesType;
  @Schema(description = "파일경로", example = "파일경로")
  private String fileUrl;
  @Schema(description = "파일경로", example = "파일경로")
  private String musicFileUrl;
  @Schema(description = "가격", example = "10000")
  private int price;
  @Schema(description = "요청수", example = "10000")
  private long requestCount;

  public MrBeatResponseDto(String id, String serviceName, int genre, int mood, int salesType,
      String fileUrl, String musicFileUrl, int price, long requestCount) {
    this.id = id;
    this.serviceName = serviceName;
    this.genre = genre;
    this.mood = mood;
    this.salesType = salesType;
    this.fileUrl = fileUrl;
    this.musicFileUrl = musicFileUrl;
    this.price = price;
    this.requestCount = requestCount;

  }

}
