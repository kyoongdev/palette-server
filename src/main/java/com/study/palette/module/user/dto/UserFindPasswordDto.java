package com.study.palette.module.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFindPasswordDto {
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email
    private String email;
}
