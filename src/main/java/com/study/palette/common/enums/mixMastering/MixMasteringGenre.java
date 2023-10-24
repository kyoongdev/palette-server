package com.study.palette.common.enums.mixMastering;

public enum MixMasteringGenre {
  BALAD(1),
  HIPHOP(2),
  TROT(3),
  DANCE(4),
  RNB(5),
  ROCK(6),
  INDI(7),
  FORK(8),
  ETC(9);
  private int genre;

  MixMasteringGenre(int genre) {
    this.genre = genre;
  }

  public int getGenre() {
    return this.genre;
  }

  public static MixMasteringGenre findMixMasteringGenre(int genre) {
    for (MixMasteringGenre mixMasteringGenre : MixMasteringGenre.values()) {
      if (mixMasteringGenre.genre == genre) {
        return mixMasteringGenre;
      }
    }
    throw new RuntimeException("MixMasteringGenre는 1 ~ 9까지 가능합니다.");
  }
}
