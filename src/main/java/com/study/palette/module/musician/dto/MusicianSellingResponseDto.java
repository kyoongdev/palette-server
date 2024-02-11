package com.study.palette.module.musician.dto;

import com.study.palette.common.enums.adminSales.ServiceType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@NoArgsConstructor
public class MusicianSellingResponseDto {

  @Schema(description = "아티스트 ID")
  private String id;

  @Schema(description = "서비스명", example = "테스트 서비스명")
  private String serviceName;

  @Schema(description = "마켓 코드", example = "true")
  private int serviceType;

  @Schema(description = "아티스트명", example = "테스트 유저명")
  private String name;

  @Schema(description = "파일경로", example = "파일경로")
  private String fileUrl;

  @Schema(description = "가격", example = "10000")
  private int price;

  private Timestamp createdAt;


  public MusicianSellingResponseDto(byte[] id, String serviceName, int serviceType, String name,
      String fileUrl, int price, Timestamp createdAt) {

    ByteBuffer bb = ByteBuffer.wrap(id);
    long high = bb.getLong();
    long low = bb.getLong();

    String uuid = new UUID(high, low).toString();


    this.id = uuid;
    this.serviceName = serviceName;
    this.serviceType = serviceType;
    this.name = name;
    this.fileUrl = fileUrl;
    this.price = price;
    this.createdAt = createdAt;

  }
}
