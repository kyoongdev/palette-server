package com.study.palette.common.enums.mrBeat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MrBeatMoodType {

  ALL(0, "전체"),
  HAPPY(1, "행복한"),
  BRIGHT(2, "밝은"),
  EXCITED(3, "신나는"),
  CALM(4, "차분한"),
  ROMANTIC(5, "낭만적인"),
  SEXY(6, "섹시한"),
  DREAMY(7, "몽환적인"),
  TENSE(8, "긴장되는"),
  DARK(9, "어두운"),
  SAD(10, "슬픈"),
  MELANCHOLY(11, "우울한");

  private int code;

  private String name;

  public static MrBeatMoodType findMrBeatMoodType(int code) {
    for (MrBeatMoodType moodType : values()) {
      if (moodType.getCode() == code) {
        return moodType;
      }
    }
    throw new RuntimeException("atmosphereType 은 0 ~ 11 까지만 가능합니다.");
  }


}
