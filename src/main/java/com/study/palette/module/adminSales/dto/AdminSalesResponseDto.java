package com.study.palette.module.adminSales.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AdminSalesResponseDto {

  @Schema(description = "서비스 명")
  String serviceName;
  @Schema(description = "서비스 타입")
  int serviceType;
  @Schema(description = "신청일")
  // 원인은 모르지만 JPASQLQuery에서 만든 쿼리 리턴값은 TimeStamp 로 리턴되고 있음
  Timestamp createdAt;
  @Schema(description = "마감일") // = 신청일 + 영업일 기준 5일
  LocalDateTime registerDeadline;

  public AdminSalesResponseDto(String serviceName, int serviceType, Timestamp createdAt) {
    this.serviceName = serviceName;
    this.serviceType = serviceType;
    this.createdAt = createdAt;
    //영업일 기준 5일 이후 날짜 계산
//    this.registerDeadline = createdAt.plusDays(5);
  }
}
