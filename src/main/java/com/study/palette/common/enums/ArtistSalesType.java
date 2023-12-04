package com.study.palette.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArtistSalesType {

  LYRICS(1, "작사"),
  FEATURED_ARTIST(2, "피처링 아티스트"),
  GUIDE_RECORDING(3, "가이드 녹음"),
  INSTRUMENT_RECORDING(4, "악기 녹음"),
  RECORD_ENGINEER(5, "레코딩 엔지니어"),
  ETC(6, "기타");

  private final int code;

  private final String name;
}
