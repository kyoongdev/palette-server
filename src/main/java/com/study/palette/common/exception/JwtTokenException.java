package com.study.palette.common.exception;

import com.study.palette.common.dto.ErrorCodeInterface;

public class JwtTokenException extends CustomException {

  public JwtTokenException(ErrorCodeInterface errorCode) {
    super(errorCode);
  }

}
