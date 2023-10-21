package com.study.palette.module.mixMastering.exception;

import com.study.palette.common.constants.ErrorCode;
import com.study.palette.common.exception.CustomException;

public class MixMasteringException extends CustomException {
  public MixMasteringException(ErrorCode e) {
    super(e);

  }
}
