package com.study.palette.module.mixMastering.dto;

import com.study.palette.common.enums.mixMastering.MixMasteringGenre;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Data;

@Data
public class MixMasteringsResponseDto {

  @Schema(description = "믹스마스터링 ID")
  private String id;
  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;
  @Schema(description = "아티스트명", example = "테스트 유저명")
  private String musicianName;
  @Schema(description = "장르", example = "1")
  private MixMasteringGenre genre;
  @Schema(description = "작업전파일", example = "파일경로")
  private String beforeJobMusic;
  @Schema(description = "작업후파일", example = "파일경로")
  private String afterJobMusic;
  @Schema(description = "가격", example = "10000")
  private int price;
  @Schema(description = "썸네일", example = "파일경로")
  private String thumbnailUrl;
  @Schema(description = "요청수", example = "10000")
  private Long requestCount;
  @Schema(description = "유저 프로필 사진", example = "파일경로")
  private String profileUrl;

  /*
   * 네임
   * 아티스트
   * 장르
   * 작업전파일
   * 작업후파일
   * 가격
   * 썸네일
   * */
  public MixMasteringsResponseDto(UUID id, String serviceName, String musicianName,
      MixMasteringGenre genre, String beforeJobMusic, String afterJobMusic, int price,
      String thumbnailUrl, Long requestCount, String profileUrl) {
    this.id = id.toString();
    this.serviceName = serviceName;
    this.musicianName = musicianName;
    this.genre = genre;
    this.beforeJobMusic = beforeJobMusic;
    this.afterJobMusic = afterJobMusic;
    this.price = price;
    this.thumbnailUrl = thumbnailUrl;
    this.requestCount = requestCount;
    this.profileUrl = profileUrl;
  }
}
