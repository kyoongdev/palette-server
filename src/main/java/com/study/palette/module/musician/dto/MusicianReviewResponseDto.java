package com.study.palette.module.musician.dto;

import com.study.palette.common.enums.adminSales.ServiceType;
import com.study.palette.common.enums.musician.ApprovalType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MusicianReviewResponseDto {

  @Schema(description = "아티스트 ID")
  private String id;

  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;

  @Schema(description = "시장명", example = "테스트 시장명")
  private int serviceType;

  @Schema(description = "판매신청일", example = "테스트 유저명")
  private Timestamp createdAt;

  @Schema(description = "서비스 상태", example = "true")
  private int approvalType;

  public MusicianReviewResponseDto(byte[] id, String serviceName, int serviceType, int approvalType,
      Timestamp createdAt) {

    // byte 배열을 ByteBuffer에 래핑
    ByteBuffer bb = ByteBuffer.wrap(id);

    // UUID의 상위 및 하위 비트 추출
    long high = bb.getLong();
    long low = bb.getLong();

    String uuid = new UUID(high, low).toString();

    this.id = uuid.toString();
    this.serviceName = serviceName;
    this.serviceType = serviceType;
    this.approvalType = approvalType;
    this.createdAt = createdAt;
  }

}
