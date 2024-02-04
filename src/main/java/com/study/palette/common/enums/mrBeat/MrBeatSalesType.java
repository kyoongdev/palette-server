package com.study.palette.common.enums.mrBeat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MrBeatSalesType {

  ALL(0, "전체"),
  SINGLE(1, "단독"),
  MANY(2, "다수");

  private int code;

  private String name;

  public static MrBeatSalesType findMrBeatSalesType(int code) {
    for (MrBeatSalesType salesType : values()) {
      if (salesType.getCode() == code) {
        return salesType;
      }
    }
    throw new RuntimeException("saleType 은 0 ~ 4 까지만 가능합니다.");
  }
}
