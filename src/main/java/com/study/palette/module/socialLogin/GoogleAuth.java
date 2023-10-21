package com.study.palette.module.socialLogin;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.palette.module.socialLogin.dto.GoogleOAuthTokenDto;
import com.study.palette.module.socialLogin.dto.GoogleUserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class GoogleAuth implements SocialOuth{

    private String googleUrl = "https://accounts.google.com";
    private String googleAuthUrl = "https://oauth2.googleapis.com/token";

    private String googleUserInfoRequestUrl = "https://www.googleapis.com/oauth2/v1/userinfo";

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleCallbackUrl;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecretKey;

    @Value("${spring.security.oauth2.client.registration.google.scope}")
    private String googleScope;

    @Override
    public String getOauthRedirectURL() {

        //parameter를 형식에 맞춰 구성해주는 함수
        String redirectURL = googleUrl + "/o/oauth2/v2/auth?client_id=" + googleClientId + "&redirect_uri=" + googleCallbackUrl
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";


        return redirectURL;
    }

    public ResponseEntity<String> requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", googleClientId);
        params.put("client_secret", googleClientSecretKey);
        params.put("redirect_uri", googleCallbackUrl);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(googleAuthUrl,params,String.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity;
        }
        return null;
    }

    public GoogleOAuthTokenDto getAccessToken(ResponseEntity<String> response) throws JsonProcessingException {
        log.info(response.getBody());

        ObjectMapper objectMapper = new ObjectMapper();

        GoogleOAuthTokenDto googleOAuthTokenDto = objectMapper.readValue(response.getBody(), GoogleOAuthTokenDto.class);
        return googleOAuthTokenDto;
    }

    public ResponseEntity<String> requestUserInfo(GoogleOAuthTokenDto oAuthToken) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + oAuthToken.getAccess_token());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(googleUserInfoRequestUrl, HttpMethod.GET, request, String.class);
        log.info("resonse.getBody() = " + response.getBody());
        return response;
    }

    public GoogleUserInfoDto getUserInfo(ResponseEntity<String> response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        GoogleUserInfoDto googleUserInfoDto = objectMapper.readValue(response.getBody(), GoogleUserInfoDto.class);
        return googleUserInfoDto;
    }
}
