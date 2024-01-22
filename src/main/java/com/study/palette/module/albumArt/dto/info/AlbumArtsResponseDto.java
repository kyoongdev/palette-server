package com.study.palette.module.albumArt.dto.info;

import com.study.palette.common.enums.albumArt.AlbumArtSaleType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumArtsResponseDto {

  @Schema(description = "앨범아트 ID")
  private String id;
  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;
  @Schema(description = "서비스 타입", example = "true")
  private int salesType;
  @Schema(description = "아티스트명", example = "테스트 유저명")
  private String musicianName;
  @Schema(description = "썸네일 경로", example = "파일경로")
  private String thumbnailUrl;
  @Schema(description = "가격", example = "10000")
  private int price;
  @Schema(description = "요청수", example = "10000")
  private long requestCount;
  @Schema(description = "유저 프로필 사진", example = "파일경로")
  private String profileUrl;

  public AlbumArtsResponseDto(UUID id, String serviceName, AlbumArtSaleType salesType, String musicianName,
      String thumbnailUrl, int price, long requestCount, String profileUrl) {
    this.id = id.toString();
    this.serviceName = serviceName;
    this.salesType = salesType.getCode();
    this.musicianName = musicianName;
    this.thumbnailUrl = thumbnailUrl;
    this.price = price;
    this.requestCount = requestCount;//TODO 추후 삭제 확인용
    this.profileUrl = profileUrl;
  }
}
