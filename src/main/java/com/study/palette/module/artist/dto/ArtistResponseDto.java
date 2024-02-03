package com.study.palette.module.artist.dto;

import com.study.palette.common.enums.ArtistSalesType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ArtistResponseDto {

  @Schema(description = "아티스트 ID")
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

  @Schema(description = "프로필 이미지", example = "프로필 이미지")
  private String profileImage;

  public ArtistResponseDto(UUID id, String serviceName, ArtistSalesType salesType, String userName,
      String fileUrl, int price, long requestCount, String profileImage) {
    this.id = id.toString();
    this.serviceName = serviceName;
    this.salesType = salesType.getCode();
    this.userName = userName;
    this.fileUrl = fileUrl;
    this.price = price;
    this.requestCount = requestCount;
    this.profileImage = profileImage;

  }


}
