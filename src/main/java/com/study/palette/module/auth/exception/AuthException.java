package com.study.palette.module.auth.exception;

import com.study.palette.common.dto.ErrorCodeInterface;
import com.study.palette.common.exception.CustomException;

public class AuthException extends CustomException {
  public AuthException(ErrorCodeInterface errorCode) {
    super(errorCode);
  }
}
