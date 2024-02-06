package com.study.palette.common.enums.adminSales;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceType {
  ALL(0, "전체"),
  MIX_MASTERING(1, "믹스마스터링"),
  ARTIST(2, "아티스트"),
  RECORDING(3, "녹음"),
  MR_BEAT(4, "MR비트"),
  ALBUM_ART(5, "앨범아트");

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

  public static List<Integer> getServiceTypeCodes() {
    return Arrays.stream(ServiceType.values())
        .map(ServiceType::getCode)
        .collect(Collectors.toList());
  }
}
