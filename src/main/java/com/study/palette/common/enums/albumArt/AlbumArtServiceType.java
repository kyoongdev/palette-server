package com.study.palette.common.enums.albumArt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AlbumArtServiceType {
  //작곡, 아티스트, 믹스마스터링, 앨범아트
  MR(10, "작곡"),
  MIX_MASTERING(20, "믹스마스터링"),
  ALBUM_ART(30, "앨범아트"),
  ARTIST(40, "아티스트");

  private final int code;
  private final String name;
}
