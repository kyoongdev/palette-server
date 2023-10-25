package com.study.palette.module.socialLogin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.palette.module.socialLogin.dto.KakaoOAuthTokenDto;
import com.study.palette.module.socialLogin.dto.KakaoUserInfoDto;
import com.study.palette.module.socialLogin.dto.NaverOAuthTokenDto;
import com.study.palette.module.socialLogin.dto.NaverUserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Log4j2
public class NaverAuth implements SocialOuth{

    private String naverTokenRequestUrl = "https://nid.naver.com/oauth2.0/token";
    private String naverUserInfoRequestUrl = "https://openapi.naver.com/v1/nid/me";

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String naverClientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String naverClientSecret;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String naverCallbackUrl;

    @Override
    public String getOauthRedirectURL() {

        String redirectUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="
                + naverClientId + "&redirect_uri=" + naverCallbackUrl;
        return redirectUrl;
    }

    public ResponseEntity<String> requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headerAccess = new HttpHeaders();
        headerAccess.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", naverClientId);
        params.add("redirect_uri", naverCallbackUrl);
        params.add("code", code);
        params.add("client_secret", naverClientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headerAccess);

        ResponseEntity<String> response = restTemplate.exchange(naverTokenRequestUrl, HttpMethod.POST, request, String.class);

        log.info(response.getBody());

        return response;
    }

    public NaverOAuthTokenDto getAccessToken(ResponseEntity<String> response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        NaverOAuthTokenDto naverOAuthTokenDto = objectMapper.readValue(response.getBody(), NaverOAuthTokenDto.class);

        return naverOAuthTokenDto;
    }

    public ResponseEntity<String> requestUserInfo(NaverOAuthTokenDto oAuthToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", oAuthToken.getToken_type() + " " + oAuthToken.getAccess_token());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(naverUserInfoRequestUrl, HttpMethod.GET, request, String.class);
        log.info("resonse.getBody() = " + response.getBody());
        return response;
    }

    public NaverUserInfoDto getUserInfo(ResponseEntity<String> response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        NaverUserInfoDto naverUserInfoDto = objectMapper.readValue(response.getBody(), NaverUserInfoDto.class);
        log.info("resonse.getBody() = " + naverUserInfoDto);
        return naverUserInfoDto;
    }
}
