package com.study.palette.module.mixMastering.dto.query;


import com.study.palette.module.mixMastering.service.MixMasteringConditions;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;

public class FindMixMasteringQuery extends MixMasteringConditions {

  @Schema(description = "전문장르", defaultValue = "0")
  @Min(value = 0, message = "전문장르는 0 ~ 4 까지만 가능합니다.")
  public int genre;

  @Schema(description = "정렬", defaultValue = "0")
  @Min(value = 0, message = "정렬은 0 ~ 3 까지만 가능합니다.")
  private int customSort;
}
