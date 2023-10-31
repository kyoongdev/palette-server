package com.study.palette.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomSort {
  POPULAR("인기순"),
  NEW("신규등록순");

  private final String name;
}
