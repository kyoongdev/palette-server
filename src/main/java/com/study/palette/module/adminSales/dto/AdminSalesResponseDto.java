package com.study.palette.module.adminSales.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@Data
public class AdminSalesResponseDto {

  @Schema(description = "서비스 명")
  String serviceName;
  @Schema(description = "서비스 타입")
  int serviceType;
  @Schema(description = "신청일")
  @Type(type = "org.hibernate.type.LocalDateTimeType")
  LocalDateTime createdAt;
  @Schema(description = "마감일") // = 신청일 + 영업일 기준 5일
  LocalDateTime registerDeadline;

  public AdminSalesResponseDto(String serviceName, int serviceType, LocalDateTime createdAt) {
    System.out.println("AdminSalesResponseDto");
    System.out.println("serviceName = " + serviceName);
    System.out.println("serviceType = " + serviceType);
    System.out.println("createdAt = " + createdAt);
    this.serviceName = serviceName;
    this.serviceType = serviceType;
    this.createdAt = createdAt;
    //영업일 기준 5일 이후 날짜 계산
//    this.registerDeadline = createdAt.plusDays(5);
  }
}
