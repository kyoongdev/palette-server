package com.study.palette.module.socialLogin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.palette.module.socialLogin.dto.KakaoOAuthTokenDto;
import com.study.palette.module.socialLogin.dto.KakaoUserInfoDto;
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
public class KakaoAuth implements SocialOuth{

    private String kakaoTokenRequestUrl = "https://kauth.kakao.com/oauth/token";
    private String kakaoUserInfoRequestUrl = "https://kapi.kakao.com/v2/user/me";
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoCallbackUrl;


    @Override
    public String getOauthRedirectURL() {

        String redirectUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=" + kakaoClientId
                + "&redirect_uri=" + kakaoCallbackUrl;


        return redirectUrl;
    }

    public ResponseEntity<String> requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headerAccess = new HttpHeaders();
        headerAccess.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", kakaoCallbackUrl);
        params.add("code", code);
        params.add("client_secret", kakaoClientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headerAccess);

        ResponseEntity<String> response = restTemplate.exchange(kakaoTokenRequestUrl, HttpMethod.POST, request, String.class);

        return response;

    }

    public KakaoOAuthTokenDto getAccessToken(ResponseEntity<String> response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoOAuthTokenDto kakaoOAuthTokenDto = objectMapper.readValue(response.getBody(), KakaoOAuthTokenDto.class);

        return kakaoOAuthTokenDto;

    }

    public ResponseEntity<String> requestUserInfo(KakaoOAuthTokenDto oAuthToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + oAuthToken.getAccess_token());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(kakaoUserInfoRequestUrl, HttpMethod.GET, request, String.class);
        log.info("resonse.getBody() = " + response.getBody());
        return response;

    }

    public KakaoUserInfoDto getUserInfo(ResponseEntity<String> response) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoUserInfoDto kakaoUserInfoDto = objectMapper.readValue(response.getBody(), KakaoUserInfoDto.class);
        log.info("resonse.getBody() = " + kakaoUserInfoDto);
        return kakaoUserInfoDto;

    }
}
