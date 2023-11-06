package com.study.palette.module.recording.dto.license;

import com.study.palette.common.enums.LicenseType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecordingLicenseInfoUpdateRequestDto {

  @Schema(description = "라이센스 타입", enumAsRef = true)
  private LicenseType licenseType;
  @Schema(description = "라이센스 이름", example = "녹음")
  private int price;
  @Schema(description = "라이센스 가격", example = "10000")
  private int useTime;
}
