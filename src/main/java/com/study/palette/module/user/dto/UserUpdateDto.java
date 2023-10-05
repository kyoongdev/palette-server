package com.study.palette.module.user.dto;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;

@Getter
@NoArgsConstructor
public class UserUpdateDto {


    private String email;
    private String password;
    private String name;
    private String phone;
    private boolean isAlarmAccept;

    @Builder
    public UserUpdateDto(String email, String password, String name, String phone, boolean isAlarmAccept) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.isAlarmAccept = isAlarmAccept;
    }
}
