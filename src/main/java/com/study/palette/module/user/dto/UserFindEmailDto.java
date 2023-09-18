package com.study.palette.module.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFindEmailDto {
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;
        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phone;
}
