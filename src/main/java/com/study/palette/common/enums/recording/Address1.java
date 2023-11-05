package com.study.palette.common.enums.recording;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Address1 {
  //    서울, 경기, 인천, 부산, 광주, 대전, 대구, 울산, 전북, 전남, 충북, 충남, 경북, 경남, 강원, 세종, 제주
  SEOUL(1), GYEONGI(2), INCHEON(3), BUSAN(4), GWANGJU(5), DAEJEON(6), DAEGU(7),
  ULSAN(8), JEONBUK(9), JEONNAM(10), CHUNGBUK(11), CHUNGNAM(12), GYEONGBUK(13),
  GYEONGNAM(14), GANGWON(15), SEJONG(16), JEJU(17);

  private int code;

  public static Address1 of(int code) {
    for (Address1 address1 : Address1.values()) {
      if (address1.getCode() == code) {
        return address1;
      }
    }
    return null;
  }

  public static String[] getNames() {
    String[] names = new String[Address1.values().length];
    for (int i = 0; i < Address1.values().length; i++) {
      names[i] = Address1.values()[i].name();
    }
    return names;
  }
}
