package com.study.palette.module.mainHome.exception;


import com.study.palette.common.dto.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MainHomeErrorCode implements ErrorCodeInterface {
  SERVICE_TYPE_NOT_FOUND(404, "서비스 타입은 1~5 까지만 가능합니다.");

  private final int status;
  private final String message;
}
