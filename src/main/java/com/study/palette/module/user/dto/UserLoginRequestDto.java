package com.study.palette.module.user.dto;

import javax.validation.constraints.NotBlank;

public class UserLoginRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phone;
    private boolean isAlarmAccept;

}
