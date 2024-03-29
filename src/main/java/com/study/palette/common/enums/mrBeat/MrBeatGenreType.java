package com.study.palette.common.enums.mrBeat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MrBeatGenreType {

  ALL(0, "전체"),

  BALLAD(1, "발라드"),
  RAP_HIPHOP(2, "랩/힙합"),
  TROT(3, "트로트"),

  DANCE(4, "댄스"),

  RNB_SOUL(5, "R&B/Soul"),

  ROCK_METAL(6, "락/메탈"),

  INDIE(7, "인디"),

  PORK_BLUES(8, "포크/블루스"),

  ETC(9, "그 외 장르");

  private int code;

  private String name;

  public static MrBeatGenreType findMrBeatGenreType(int code) {
    for (MrBeatGenreType genreType : values()) {
      if (genreType.getCode() == code) {
        return genreType;
      }
    }
    throw new RuntimeException("genreType 은 0 ~ 9 까지만 가능합니다.");
  }


}
