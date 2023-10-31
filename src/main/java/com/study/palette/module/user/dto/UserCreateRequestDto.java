package com.study.palette.module.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequestDto {

  @NotBlank(message = "이메일을 입력해주세요.")
  @Email
  String email;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  String password;

  @NotBlank(message = "이름을 입력해주세요.")
  String name;

  @NotBlank(message = "전화번호를 입력해주세요.")
  @Size(max = 11, message = "전화번호는 11자리를 넘을 수 없습니다.")
  String phone;

  @NotNull(message = "알람 수신 여부를 입력해주세요.")
  boolean isAlarmAccept;

}
