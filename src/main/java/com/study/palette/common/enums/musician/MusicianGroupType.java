package com.study.palette.common.enums.musician;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MusicianGroupType {

  SOLO(0, "솔로"),
  GROUP(1, "그룹");

  private int code;

  private String name;

  public static MusicianGroupType findMusicianGroupType(int code) {
    for (MusicianGroupType musicianGroupType : MusicianGroupType.values()) {
      if (musicianGroupType.code == code) {
        return musicianGroupType;
      }
    }
    throw new RuntimeException("type은 1 ~ 2 까지만 가능합니다.");
  }
}
