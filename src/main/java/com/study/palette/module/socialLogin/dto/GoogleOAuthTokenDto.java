package com.study.palette.module.socialLogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoogleOAuthTokenDto {
    private String access_token;
    private Integer expires_in;

    private String scope;

    private String token_type;

    private String id_token;
}
