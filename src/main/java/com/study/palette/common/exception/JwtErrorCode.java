package com.study.palette.common.exception;

import com.study.palette.common.dto.ErrorCodeInterface;

public enum JwtErrorCode implements ErrorCodeInterface {
  EXPIRED_JWT_EXCEPTION(401, "만료된 토큰입니다."),
  INVALID_JWT_EXCEPTION(401, "유효하지 않은 토큰입니다."),
  ACCESS_DENIED_EXCEPTION(403, "접근 권한이 없습니다."),
  ANONYMOUS_JWT_TOKEN_EXCEPTION(401, "알수없는 토큰 오류");

  private final int status;
  private final String message;

  JwtErrorCode(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public int getStatus() {
    return this.status;
  }

  public String getMessage() {
    return this.message;
  }
}
