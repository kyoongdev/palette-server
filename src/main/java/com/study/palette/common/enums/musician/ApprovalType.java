package com.study.palette.common.enums.musician;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApprovalType {

  // 재능마켓 서비스 상태
  // 0: 승인대기, 1: 비승인, 2: 판매중지
  WAITING(1, "승인대기"),
  REJECTED(2, "비승인"),
  STOPPED(3, "판매중지"),
  APPROVED(4, "승인완료");


  private final int code;
  private final String name;

  public static ApprovalType findMarketApprovalType(int code) {
    for (ApprovalType status : ApprovalType.values()) {
      if (status.code == code) {
        return status;
      }
    }
    throw new RuntimeException("status 는 1 ~ 4 까지만 가능합니다.");
  }

}
