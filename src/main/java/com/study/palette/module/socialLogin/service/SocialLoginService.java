package com.study.palette.module.socialLogin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.palette.module.socialLogin.*;
import com.study.palette.module.socialLogin.dto.*;
import com.study.palette.module.user.entity.Role;
import com.study.palette.module.user.entity.SocialType;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SocialLoginService {
    private final GoogleAuth googleAuth;
    private final KakaoAuth kakaoAuth;

    private final NaverAuth naverAuth;

    private final UserRepository userRepository;

    @Autowired
    public SocialLoginService(GoogleAuth googleAuth, KakaoAuth kakaoAuth, NaverAuth naverAuth, UserRepository userRepository) {
        this.googleAuth = googleAuth;
        this.kakaoAuth = kakaoAuth;
        this.naverAuth = naverAuth;
        this.userRepository = userRepository;
    }

    public String redirectUrl(SocialType socialLoginType) {

        String returnUrl = "";

        if(socialLoginType.equals(SocialType.GOOGLE)) {

            returnUrl = googleAuth.getOauthRedirectURL();
        } else if(socialLoginType.equals(SocialType.KAKAO)){
            returnUrl = kakaoAuth.getOauthRedirectURL();
        } else if(socialLoginType.equals(SocialType.NAVER)) {
            returnUrl = naverAuth.getOauthRedirectURL();
        }

        return returnUrl;

    }


    public String socialLogin(String code, SocialType socialType) throws JsonProcessingException {

        //TODO: 소셜로그인 타입 UserInfoDto로 변환 후 작업 필요
        if(socialType.equals(SocialType.GOOGLE)) {
            GoogleUserInfoDto user = getGoogleUserInfoDto(code);
            User findUser = userRepository.findBySocialTypeAndSocialId(socialType, user.getId()).orElse(null);

            if(findUser == null) {
                User createUser = User.builder()
                        .role(Role.MEMBER)
                        .email(user.getEmail())
                        .name(user.getName())
                        .socialType(socialType)
                        .socialId(user.getId())
                        .build();

            String id = userRepository.save(createUser).getId().toString();
            return id;
            }
        } else if(socialType.equals(SocialType.KAKAO)) {
            KakaoUserInfoDto user = getKakaoUserInfoDto(code);
            User findUser = userRepository.findBySocialTypeAndSocialId(socialType, String.valueOf(user.getId())).orElse(null);

            if(findUser == null) {
                User createUser = User.builder()
                        .role(Role.MEMBER)
                        .email(user.getKakao_account().getEmail())
                        .name(user.getKakao_account().getProfile().getNickname())
                        .socialType(socialType)
                        .socialId(String.valueOf(user.getId()))
                        .build();

            String id = userRepository.save(createUser).getId().toString();
            return id;
            }

        } else if(socialType.equals(SocialType.NAVER)){
            NaverUserInfoDto user = getNaverUserInfoDto(code);

            User findUser = userRepository.findBySocialTypeAndSocialId(socialType, user.getResponse().getId()).orElse(null);

            if(findUser == null) {
                User createUser = User.builder()
                        .role(Role.MEMBER)
                        .email(user.getResponse().getEmail())
                        .name(user.getResponse().getName())
                        .socialType(socialType)
                        .socialId(user.getResponse().getId())
                        .build();

            String id = userRepository.save(createUser).getId().toString();
            return id;
            }
        }

        return null;
    }

    //TODO: 각 소셜에서 User정보 가져오는 메소드 통합 필요
    private GoogleUserInfoDto getGoogleUserInfoDto(String code) throws JsonProcessingException {
        ResponseEntity<String> accessTokenResponse = googleAuth.requestAccessToken(code);
        GoogleOAuthTokenDto oAuthToken = googleAuth.getAccessToken(accessTokenResponse);
        ResponseEntity<String> userInfoResponse = googleAuth.requestUserInfo(oAuthToken);
        GoogleUserInfoDto googleUser = googleAuth.getUserInfo(userInfoResponse);
        return googleUser;
    }

    private KakaoUserInfoDto getKakaoUserInfoDto(String code) throws JsonProcessingException {
        ResponseEntity<String> accessTokenResponse = kakaoAuth.requestAccessToken(code);
        KakaoOAuthTokenDto oAuthToken = kakaoAuth.getAccessToken(accessTokenResponse);
        ResponseEntity<String> userInfoResponse = kakaoAuth.requestUserInfo(oAuthToken);
        KakaoUserInfoDto kakaoUser = kakaoAuth.getUserInfo(userInfoResponse);
        return kakaoUser;
    }

    private NaverUserInfoDto getNaverUserInfoDto(String code) throws JsonProcessingException {
        ResponseEntity<String> accessTokenResponse = naverAuth.requestAccessToken(code);
        NaverOAuthTokenDto oAuthToken = naverAuth.getAccessToken(accessTokenResponse);
        ResponseEntity<String> userInfoResponse = naverAuth.requestUserInfo(oAuthToken);
        NaverUserInfoDto naverUser = naverAuth.getUserInfo(userInfoResponse);
        return naverUser;
    }


}
