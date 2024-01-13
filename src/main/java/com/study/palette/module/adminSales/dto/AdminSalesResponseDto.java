package com.study.palette.module.adminSales.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AdminSalesResponseDto {

  @Schema(description = "서비스 명")
  String serviceName;
  @Schema(description = "서비스 타입")
  String serviceType;
  @Schema(description = "신청일")
  LocalDateTime createdAt;
  @Schema(description = "마감일") // = 신청일 + 영업일 기준 5일
  LocalDateTime registerDeadline;

  public AdminSalesResponseDto(String serviceName, String serviceType, LocalDateTime createdAt) {
    this.serviceName = serviceName;
    this.serviceType = serviceType;
    this.createdAt = createdAt;
    //영업일 기준 5일 이후 날짜 계산
    this.registerDeadline = createdAt.plusDays(5);
  }
}
