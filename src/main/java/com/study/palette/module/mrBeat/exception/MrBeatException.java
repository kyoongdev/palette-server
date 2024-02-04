package com.study.palette.module.mrBeat.exception;

import com.study.palette.common.dto.ErrorCodeInterface;
import com.study.palette.common.exception.CustomException;

public class MrBeatException extends CustomException {

    public MrBeatException(ErrorCodeInterface e) {
      super(e);

    }

}
