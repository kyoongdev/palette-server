package com.study.palette.module.mainHome.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class TodayMusicianDto {
  @Schema(description = "서비스 ID")
  private String id;
  @Schema(description = "서비스 이름")
  private String serviceName;
  @Schema(description = "서비스 썸네일")
  private String thumbnailUrl;
  @Schema(description = "아티스트 이름")
  private String musicianName;
  @Schema(description = "프로필 Url")
  private String profileUrl;
  @Schema(description = "해시태그(판매타입)")
  private String salesType;
  @Schema(description = "가격")
  private int price;
}
