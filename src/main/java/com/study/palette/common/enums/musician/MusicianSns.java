package com.study.palette.common.enums.musician;

public enum MusicianSns {

  INSTAGRAM(1),
  TWITTER(2),
  FACEBOOK(3),
  YOUTUBE(4),
  ETC(5);

  private int type;

  MusicianSns(int type) {
    this.type = type;
  }

  public int getMusicianSns() {
    return this.type;
  }

  public static MusicianSns findMusicianSns(int type) {
    for (MusicianSns musicianSns : MusicianSns.values()) {
      if (musicianSns.type == type) {
        return musicianSns;
      }
    }
    throw new RuntimeException("type은 1 ~ 5 까지만 가능합니다.");
  }

}
