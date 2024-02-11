package com.study.palette.module.musician.exception;

import com.study.palette.common.dto.ErrorCodeInterface;
import com.study.palette.common.exception.CustomException;

public class MusicianException extends CustomException {

  public MusicianException(ErrorCodeInterface e) {
    super(e);
  }

}
