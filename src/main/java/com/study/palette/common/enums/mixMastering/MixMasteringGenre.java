package com.study.palette.common.enums.mixMastering;

public enum MixMasteringGenre {
  ALL(0),
  BALAD(1),
  HIPHOP(2),
  TROT(3),
  DANCE(4),
  RNB(5),
  ROCK(6),
  INDI(7),
  FORK(8),
  ETC(9);
  private int code;

  MixMasteringGenre(int code) {
    this.code = code;
  }

  public int getCode() {
    return this.code;
  }

  public static MixMasteringGenre findMixMasteringGenre(int code) {

    for (MixMasteringGenre mixMasteringGenre : MixMasteringGenre.values()) {
      if (mixMasteringGenre.code == code) {
        return mixMasteringGenre;
      }
    }
    throw new RuntimeException("MixMasteringGenre는 0 ~ 9까지 가능합니다.");
  }
}
