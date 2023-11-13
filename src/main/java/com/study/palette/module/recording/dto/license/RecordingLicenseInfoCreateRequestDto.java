package com.study.palette.module.recording.dto.license;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecordingLicenseInfoCreateRequestDto {

  @Schema(description = "라이센스 타입", example = "1")
  private int licenseType;
  @Schema(description = "가격", example = "10000")
  private int price;
  @Schema(description = "이용시간", example = "1")
  private int useTime;
}