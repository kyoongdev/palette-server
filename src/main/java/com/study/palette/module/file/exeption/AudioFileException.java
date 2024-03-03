package com.study.palette.module.file.exeption;

import com.study.palette.common.dto.ErrorCodeInterface;
import com.study.palette.common.exception.CustomException;

public class AudioFileException extends CustomException {

  public AudioFileException(ErrorCodeInterface errorCode) {
    super(errorCode);
  }
}
