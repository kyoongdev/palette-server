package com.study.palette.common.enums.recording;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Address1 {
    //    서울, 경기, 인천, 부산, 광주, 대전, 대구, 울산, 전북, 전남, 충북, 충남, 경북, 경남, 강원, 세종, 제주
    서울(1), 경기(2), 인천(3), 부산(4), 광주(5), 대전(6), 대구(7), 울산(8), 전북(9), 전남(10), 충북(11), 충남(12), 경북(13), 경남(14), 강원(15), 세종(16), 제주(17);

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
