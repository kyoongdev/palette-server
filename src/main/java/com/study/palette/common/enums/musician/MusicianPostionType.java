package com.study.palette.common.enums.musician;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MusicianPostionType {

  ALL(0, "전체"),
  ALBUM_ART(1, "앨범아트"),
  ARTIST(2, "아티스트"),
  MIX_MASTERING(3, "믹스마스터링"),
  RECORDING(4, "녹음"),
  MR_BEAT(5, "MR비트");

  private int code;
  private String name;


  public static MusicianPostionType findPositionType(int code) {
    for (MusicianPostionType musicianPostionType : MusicianPostionType.values()) {
      if (musicianPostionType.code == code) {
        return musicianPostionType;
      }
    }
    throw new RuntimeException("type은 1 ~ 5 까지만 가능합니다.");
  }
}
