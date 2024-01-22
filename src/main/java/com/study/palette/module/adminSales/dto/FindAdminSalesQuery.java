package com.study.palette.module.adminSales.dto;


import com.study.palette.module.adminSales.service.AdminSalesConditions;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;

public class FindAdminSalesQuery extends AdminSalesConditions {

  @Schema(description = "판매글 등록 완료 여부", example = "true")
  @NotNull
  private Boolean isRegistrationCompleted;
}
