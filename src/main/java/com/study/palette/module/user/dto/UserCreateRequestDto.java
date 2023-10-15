package com.study.palette.module.user.dto;

import com.study.palette.module.user.entity.Role;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserCreateRequestDto {


  //TODO: role은 swagger에는 표기 X
  @NotBlank
  private Role role;
  @NotBlank
  String email;
  @NotBlank
  String password;
  @NotBlank
  String name;

  //TODO: phone -> 11자리 validation
  @NotBlank
  String phone;
  @NotBlank
  boolean isAlarmAccept;

}
