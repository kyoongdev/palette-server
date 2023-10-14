package com.study.palette.module.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {


  @Email
  private String email;

  private String password;


  private String name;

  private String phone;

  private boolean isAlarmAccept;

}
