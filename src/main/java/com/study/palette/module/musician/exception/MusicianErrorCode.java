package com.study.palette.module.musician.exception;

import com.study.palette.common.dto.ErrorCodeInterface;

public enum MusicianErrorCode implements ErrorCodeInterface {

  MUSICIAN_NOT_EXIST(404, "해당 뮤지션을 찾을 수 없습니다."),
  MUSICIAN_NOT_AUTHORIZED(403, "승인 되지 않은 뮤지션입니다."),
  MUSICIAN_NOT_EXCLUDEID(404, "현재 서비스 정보가 없습니다."),

  MUSICIAN_EXIST(409, "이미 등록된 뮤지션입니다.");



  private final int status;
  private final String message;

  MusicianErrorCode(int status, String message) {
    this.status = status;
    this.message = message;
  }

  @Override
  public int getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
