package com.study.palette.module.albumArt.exception;

import com.study.palette.common.dto.ErrorCodeInterface;
import com.study.palette.common.exception.CustomException;

public class AlbumArtException extends CustomException {
  public AlbumArtException(ErrorCodeInterface errorCode) {
    super(errorCode);
  }
}
