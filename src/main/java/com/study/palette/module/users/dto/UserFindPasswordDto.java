package com.study.palette.module.users.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFindPasswordDto {

  @NotBlank(message = "이메일을 입력해주세요.")
  @Email
  private String email;
}
