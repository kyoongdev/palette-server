package com.study.palette.module.user.exception;


import com.study.palette.common.dto.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeInterface {

  USER_NOT_FOUND(404, "유저를 찾을 수 없습니다."),
  PASSWORD_BAD_REQUEST(400, "비밀번호가 일치하지 않습니다."),
  EXISTS_USER_ERROR(409, "이미 존재하는 유저입니다.");

  private final int status;
  private final String message;
}
