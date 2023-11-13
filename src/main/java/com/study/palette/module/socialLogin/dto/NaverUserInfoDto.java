package com.study.palette.module.socialLogin.dto;

import lombok.*;

@Data
public class NaverUserInfoDto {

    private String resultcode;

    private String message;

    private response response;

    @Data
    public class response {
        private String id;
        private String email;

        private String mobile;
        private String mobile_e164;
        private String name;
    }



}
