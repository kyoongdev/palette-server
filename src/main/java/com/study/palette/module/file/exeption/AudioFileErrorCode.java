package com.study.palette.module.file.exeption;

import com.study.palette.common.dto.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AudioFileErrorCode implements ErrorCodeInterface {

  AUDIO_FILE_NOT_CONVERT(500, "오디오 파일 변환 중 오류가 발생했습니다."),
  AUDIO_FILE_NOT_DELETE_TEMP(500, "임시파일 삭제 중 오류가 발생했습니다");


  private final int status;
  private final String message;
}
