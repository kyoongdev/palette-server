package com.study.palette.module.mrBeat.exception;

import com.study.palette.common.dto.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MrBeatErrorCode implements ErrorCodeInterface {

  MRBEAT_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
  MRBEAT_NOT_YOURS(403, "본인이 작성하지 않은 게시글입니다."),
  MRBEAT_NOT_SORT(404, "정렬 데이터가 없습니다."),
  MRBEAT_REQUEST_ALREADY_EXISTS(409, "오늘 이미 의뢰한 게시글입니다.");

  private final int status;
  private final String message;


}
