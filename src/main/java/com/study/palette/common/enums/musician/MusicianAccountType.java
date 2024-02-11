package com.study.palette.common.enums.musician;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MusicianAccountType {
  KB(1, "국민은행"),
  SHINHAN(2, "신한은행"),
  WOORI(3, "우리은행"),
  KAKAO(4, "카카오뱅크"),
  NONGHYUP(5, "농협은행"),
  KEB(6, "외환은행"),
  SC(7, "SC은행"),
  CITY(8, "시티은행"),
  KDB(9, "산업은행"),
  HANA(10, "하나은행"),
  TOSS(11, "토스뱅크"),
  ETC(12, "기타");

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
