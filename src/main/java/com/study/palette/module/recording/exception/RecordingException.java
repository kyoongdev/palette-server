package com.study.palette.module.recording.exception;

import com.study.palette.common.dto.ErrorCodeInterface;
import com.study.palette.common.exception.CustomException;

public class RecordingException extends CustomException {

  public RecordingException(ErrorCodeInterface errorCode) {
    super(errorCode);
  }
}
