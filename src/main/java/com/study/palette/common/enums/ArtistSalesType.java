package com.study.palette.common.enums;

import com.study.palette.common.enums.albumArt.AlbumArtSaleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArtistSalesType {


  ALL(0, "전체"),
  LYRICS(1, "작사"),
  FEATURED_ARTIST(2, "피처링"),
  GUIDE_RECORDING(3, "가이드 녹음"),
  INSTRUMENT_RECORDING(4, "악기 녹음"),
  RECORD_ENGINEER(5, "레코딩 엔지니어"),
  ETC(6, "그외 서비스");

  private final int code;

  private final String name;


  public static ArtistSalesType findArtistSalesType(int code) {
    for (ArtistSalesType salesType : ArtistSalesType.values()) {
      if (salesType.code == code) {
        return salesType;
      }
    }
    throw new RuntimeException("saleType 은 0 ~ 6 까지만 가능합니다.");
  }
}
