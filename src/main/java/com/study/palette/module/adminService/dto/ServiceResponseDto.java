package com.study.palette.module.adminService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceResponseDto {

  @Schema(description = "서비스 명")
  String serviceName;
  @Schema(description = "서비스 타입")
  String serviceType;
  @Schema(description = "신청일")
  String createdAt;
  @Schema(description = "마감일") // = 신청일 + 영업일 기준 5일
  String registerDeadline;
}
