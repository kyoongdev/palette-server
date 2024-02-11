package com.study.palette.common.enums.musician;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MusicianAuthorizedType {

  // 음악인 인증 상태
  // 1: 인증대기, 2: 인증완료, 3: 인증거절
  WAITING(1, "인증대기"),
  APPROVED(2, "승인"),
  REJECTED(3, "반려");

  private final int code;
  private final String name;

  public static MusicianAuthorizedType findMusicianAuthorizedType(int code) {
    for (MusicianAuthorizedType status : MusicianAuthorizedType.values()) {
      if (status.code == code) {
        return status;
      }
    }
    throw new RuntimeException("status 는 1 ~ 3 까지만 가능합니다.");
  }

}
