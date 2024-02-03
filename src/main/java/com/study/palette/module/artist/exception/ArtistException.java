package com.study.palette.module.artist.exception;

import com.study.palette.common.constants.ErrorCode;
import com.study.palette.common.dto.ErrorCodeInterface;
import com.study.palette.common.exception.CustomException;

public class ArtistException extends CustomException {

  public ArtistException(ErrorCodeInterface e) {
    super(e);

  }
}
