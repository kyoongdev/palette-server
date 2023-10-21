package com.study.palette.module.socialLogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NaverOAuthTokenDto {
    private String refresh_token;
    private String expires_in;

    private String token_type;

    private String access_token;

}
