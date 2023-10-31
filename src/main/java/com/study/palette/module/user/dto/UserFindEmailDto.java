package com.study.palette.module.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFindEmailDto {

  @NotBlank(message = "이름을 입력해주세요.")
  private String name;

  @NotBlank(message = "전화번호를 입력해주세요.")
  @Size(max = 11, message = "전화번호는 11자리를 넘을 수 없습니다.")
  private String phone;
}
