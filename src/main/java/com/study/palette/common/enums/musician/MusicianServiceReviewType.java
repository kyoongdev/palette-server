package com.study.palette.common.enums.musician;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MusicianServiceReviewType {

  // Musician 서비스 상태
  // 1: 승인대기, 2: 비승인, 3: 승인거절 4. 판매중지
  WAITING(0, "승인대기"),
  APPROVED(1, "비승인"),
  REJECTED(2, "승인거절"),
  STOPPED(3, "판매중지");


  private final int code;
  private final String name;

  public static MusicianServiceReviewType findMusicianServicePending(int code) {
    for (MusicianServiceReviewType status : MusicianServiceReviewType.values()) {
      if (status.code == code) {
        return status;
      }
    }
    throw new RuntimeException("status 는 1 ~ 4 까지만 가능합니다.");
  }

}
