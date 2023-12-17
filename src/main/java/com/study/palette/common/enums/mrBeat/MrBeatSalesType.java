package com.study.palette.common.enums.mrBeat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MrBeatSalesType {

  SINGLE(1, "단독"),
  MANY(2, "다수");

  private int code;

  private String name;
}
