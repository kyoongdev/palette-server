package com.study.palette.module.mainHome.exception;

import com.study.palette.common.dto.ErrorCodeInterface;
import com.study.palette.common.exception.CustomException;

public class MainHomeException extends CustomException {

  public MainHomeException(ErrorCodeInterface errorCode) {
    super(errorCode);
  }

}
