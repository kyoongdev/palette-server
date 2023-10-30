package com.study.palette.module.recording.exception;


import com.study.palette.common.dto.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecordingErrorCode implements ErrorCodeInterface {

  RECORDING_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
  RECORDING_NOT_YOURS(403, "본인이 작성하지 않은 게시글입니다."),
  RECORDING_NOT_SORT(404, "정렬 데이터가 없습니다.");

//    INVALID_RECORDING_DATA(400, "올바르지 않은 게시글 데이터입니다."),
//    RECORDING_ALREADY_EXISTS(409, "이미 존재하는 게시글입니다.");


  private final int status;
  private final String message;
}
