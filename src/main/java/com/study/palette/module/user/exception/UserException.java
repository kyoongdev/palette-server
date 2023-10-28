package com.study.palette.module.user.exception;

import com.study.palette.common.dto.ErrorCodeInterface;
import com.study.palette.common.exception.CustomException;

public class UserException extends CustomException {
  public UserException(ErrorCodeInterface errorCode) {
    super(errorCode);
  }
}
