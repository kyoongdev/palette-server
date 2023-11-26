package com.study.palette.module.albumArt.dto.info;

import com.study.palette.common.dto.CommonResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;


public class AlbumArtsResponseDto {

  @Schema(description = "앨범아트 ID")
  private String id;
  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;
  @Schema(description = "서비스 타입", example = "true")
  private int salesType;
  @Schema(description = "아티스트명", example = "테스트 유저명")
  private String userName;
  @Schema(description = "파일경로", example = "파일경로")
  private String fileUrl;
  @Schema(description = "가격", example = "10000")
  private int price;
  @Schema(description = "요청수", example = "10000")
  private long requestCount;

  public AlbumArtsResponseDto(UUID id, String serviceName, int salesType, String userName,
      String fileUrl, int price, long requestCount) {
    this.id = id.toString();
    this.serviceName = serviceName;
    this.salesType = salesType;
    this.userName = userName;
    this.fileUrl = fileUrl;
    this.price = price;
    this.requestCount = requestCount;//TODO 추후 삭제 확인용
  }
}
