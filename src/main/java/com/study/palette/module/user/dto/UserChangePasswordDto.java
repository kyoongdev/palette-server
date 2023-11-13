package com.study.palette.module.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserChangePasswordDto {

  @Schema(description = "현재 비밀번호")
  private String password;
  @Schema(description = "새 비밀번호")
  private String newPassword;

}
