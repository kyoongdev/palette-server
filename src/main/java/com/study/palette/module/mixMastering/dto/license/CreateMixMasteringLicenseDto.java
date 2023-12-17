package com.study.palette.module.mixMastering.dto.license;


import com.study.palette.common.enums.LicenseType;
import com.study.palette.module.mixMastering.entity.MixMasteringInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateMixMasteringLicenseDto {
  @Schema(description = "라이센스 유형")
  private int licenseType;
  @Schema(description = "가격")
  private int price;

  @Schema(description = "제공 파일")
  private String servedType;

  @Schema(description = "수정 횟수")
  private int updateCount;

  @Schema(description = "작업 기간")
  private int period;

  @Schema(description = "시안 개수")
  private int draftCount;

  @Schema(description = "저작권 양도 여부")
  private boolean isAssign;
  @Schema(description = "성업적 사용 가능 여부")
  private boolean isUseCommercial;
  @Schema(description = "원본 파일 제공 여부")
  private boolean isServeOriginFile;

  @Schema(description = "응용 활용 여부")
  private boolean isOtherUseApproved;

  public MixMasteringLicenseInfo toEntity(MixMasteringInfo mixMasteringInfo) {
    return MixMasteringLicenseInfo.builder()
            .licenseType(this.licenseType)
            .price(this.price)
            .servedType(this.servedType)
            .updateCount(this.updateCount)
            .period(this.period)
            .draftCount(this.draftCount)
            .isAssign(this.isAssign)
            .isUseCommercial(this.isUseCommercial)
            .isServeOriginFile(this.isServeOriginFile)
            .isOtherUseApproved(this.isOtherUseApproved)
            .mixMasteringInfo(mixMasteringInfo)
            .build();

  }

}
