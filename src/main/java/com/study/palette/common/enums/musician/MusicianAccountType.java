package com.study.palette.common.enums.musician;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MusicianAccountType {
  KB(0, "국민은행"),
  SHINHAN(1, "신한은행"),
  WOORI(2, "우리은행"),
  KAKAO(3, "카카오뱅크"),
  NONGHYUP(4, "농협은행"),
  KEB(5, "외환은행"),
  SC(6, "SC은행"),
  CITY(7, "시티은행"),
  KDB(8, "산업은행"),
  HANA(9, "하나은행"),
  TOSS(10, "토스뱅크"),
  ETC(11, "기타");

  private int code;

  private String bank;


  public static MusicianAccountType findMusicianAccount(int code) {
    for (MusicianAccountType musicianAccount : MusicianAccountType.values()) {
      if (musicianAccount.code == code) {
        return musicianAccount;
      }
    }
    throw new RuntimeException("musicianAccount 는 1 ~ 12 까지만 가능합니다.");
  }
}
