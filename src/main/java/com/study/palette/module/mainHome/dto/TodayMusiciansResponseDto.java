package com.study.palette.module.mainHome.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TodayMusiciansResponseDto {

  @Schema(description = "서비스타입", example = "1")
  private int serviceType;
  @Schema(description = "서비스 ID")
  private String id;
  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;
  @Schema(description = "가격", example = "10000")
  private int price;
  @Schema(description = "뮤지션 이름", example = "테스트 유저명")
  private String musicianName;
  @Schema(description = "태그", example = "발라드")
  private String tag;
  @Schema(description = "썸네일 경로", example = "파일경로")
  private String thumbnailUrl;
  @Schema(description = "유저 프로필 사진", example = "파일경로")
  private String profileUrl;
}
