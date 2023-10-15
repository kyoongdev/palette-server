package com.study.palette.common.exception;

import com.study.palette.common.dto.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
  private final ErrorCodeInterface errorCode;
}
