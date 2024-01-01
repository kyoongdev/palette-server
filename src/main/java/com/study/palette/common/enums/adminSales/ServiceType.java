package com.study.palette.common.enums.adminSales;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceType {
  ALL(0, "전체"),
  ALBUM_ART(1, "앨범아트"),
  ARTIST(2, "아티스트"),
  MIX_MASTERING(3, "믹스마스터링"),
  RECORDING(4, "녹음"),
  MR_BEAT(5, "MR비트");

  private int code;
  private String name;

  public static ServiceType findServiceType(int code) {
    for (ServiceType serviceType : ServiceType.values()) {
      if (serviceType.getCode() == code) {
        return serviceType;
      }
    }
    throw new RuntimeException("서비스 타입은 0~5까지만 가능합니다");
  }

}
