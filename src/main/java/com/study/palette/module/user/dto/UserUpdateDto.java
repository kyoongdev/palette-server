package com.study.palette.module.user.dto;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserUpdateDto {


    private String email;
    private String name;
    @Size(max = 11, message = "전화번호는 11자리를 넘을 수 없습니다.")
    private String phone;
    private boolean isAlarmAccept;

    @Builder
    public UserUpdateDto(String email, String name, String phone, boolean isAlarmAccept) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.isAlarmAccept = isAlarmAccept;
    }
}
