package com.study.palette.common.enums.albumArt;

import com.study.palette.common.enums.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlbumArtSaleType {
  // 앨범아트 판매유형
//  판매유형
//  전체, 사진편집, 일러스트, 그래픽아트, 그외 장르
  ALL(0, "전체"),
  PHOTO_EDITING(1, "사진편집"),
  ILLUSTRATION(2, "일러스트"),
  GRAPHIC_ART(3, "그래픽아트"),
  OTHERS(4, "그외 장르");

  private final int code;
  private final String name;

  public static AlbumArtSaleType findAlbumArtSaleType(int code) {
    for (AlbumArtSaleType saleType : AlbumArtSaleType.values()) {
      if (saleType.code == code) {
        return saleType;
      }
    }
    throw new RuntimeException("saleType 은 0 ~ 4 까지만 가능합니다.");
  }
}
