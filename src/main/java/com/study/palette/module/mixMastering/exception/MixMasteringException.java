package com.study.palette.module.mixMastering.exception;

import com.study.palette.common.dto.ErrorCodeInterface;
import com.study.palette.common.exception.CustomException;

public class MixMasteringException extends CustomException {
  public MixMasteringException(ErrorCodeInterface e) {
    super(e);
  }
}
