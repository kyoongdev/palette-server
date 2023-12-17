package com.study.palette.module.adminSales.dto;


import com.study.palette.common.dto.PageDto;
import com.study.palette.module.adminSales.service.AdminSalesConditions;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class FindAdminSalesQuery extends AdminSalesConditions {
  @Schema(description = "판매글 등록 완료 여부", defaultValue = "0", example = "0: 미완료, 1: 완료")
  private int isRegistrationCompleted;
}
