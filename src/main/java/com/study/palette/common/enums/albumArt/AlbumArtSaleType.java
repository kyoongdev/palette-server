package com.study.palette.common.enums.albumArt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlbumArtSaleType {
  // 앨범아트 판매유형
//  판매유형
//  전체, 사진편집, 일러스트, 그래픽아트, 그외 장르
  PHOTO_EDITING(10, "사진편집"),
  ILLUSTRATION(20, "일러스트"),
  GRAPHIC_ART(30, "그래픽아트"),
  OTHERS(40, "그외 장르");

  private final int code;
  private final String name;
}
