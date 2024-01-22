package com.study.palette.module.recording.dto.info;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecordingResponseDto {

  @Schema(description = "녹음 서비스 아이디")
  private String id;
  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;
  @Schema(description = "녹음 엔지니어링 제공 여부", example = "true")
  private boolean isRecordingEngineering;
  @Schema(description = "아티스트명", example = "테스트 유저명")
  private String musicianName;
  @Schema(description = "파일경로", example = "파일경로")
  private String thumbnailUrl;
  @Schema(description = "가격", example = "10000")
  private int price;
  @Schema(description = "유저 프로필 사진", example = "파일경로")
  private String profileUrl;

  public RecordingResponseDto(UUID id, String serviceName, boolean isRecordingEngineering, String musicianName,
      String thumbnailUrl, int price, String profileUrl) {
    this.id = id.toString();
    this.serviceName = serviceName;
    this.isRecordingEngineering = isRecordingEngineering;
    this.musicianName = musicianName;
    this.thumbnailUrl = thumbnailUrl;
    this.price = price;
    this.profileUrl = profileUrl;
  }
}