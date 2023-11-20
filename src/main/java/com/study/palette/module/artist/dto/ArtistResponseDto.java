package com.study.palette.module.artist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ArtistResponseDto {

  private String id;
  private String serviceName;

  private int salesType;

  private String userName;

  private String fileUrl;

  private int price;

  private long requestCount;

  public ArtistResponseDto(String id, String serviceName, int salesType, String userName,
      String fileUrl, int price, long requestCount) {
    this.id = id;
    this.serviceName = serviceName;
    this.salesType = salesType;
    this.userName = userName;
    this.fileUrl = fileUrl;
    this.price = price;
    this.requestCount = requestCount;

  }


}
