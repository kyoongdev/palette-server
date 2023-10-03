package com.study.palette.module.user.dto;

import com.study.palette.module.user.entity.Role;
import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class UserCreateRequestDto {

    @NotBlank
    private Role role;
    @NotBlank
    String email;
    @NotBlank
    String password;
    @NotBlank
    String name;
    @NotBlank
    String phone;
    @NotBlank
    boolean isAlarmAccept;

}
