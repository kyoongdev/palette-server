package com.study.palette.module.adminService.dto;


import com.study.palette.common.dto.PageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindAdminServiceQuery extends PageDto {

  @Schema(description = "서비스 타입")
  String serviceType;
  @Schema(description = "판매글 등록 완료 여부")
  boolean isRegistrationCompleted;
}
