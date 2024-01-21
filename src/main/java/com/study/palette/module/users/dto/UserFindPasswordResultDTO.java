package com.study.palette.module.users.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFindPasswordResultDTO {

  @Schema(name="신규 비밀번호")
  private String newPassword;
}
