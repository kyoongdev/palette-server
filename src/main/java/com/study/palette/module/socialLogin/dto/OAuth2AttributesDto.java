package com.study.palette.module.socialLogin.dto;

import com.study.palette.module.socialLogin.GoogleOAuth2UserInfo;
import com.study.palette.module.socialLogin.KakaoOAuth2UserInfo;
import com.study.palette.module.socialLogin.NaverOAuth2UserInfo;
import com.study.palette.module.socialLogin.OAuth2UserInfo;
import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.SocialType;
import com.study.palette.module.users.entity.Users;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

/**
 * 각 소셜에서 받아오는 데이터가 다르므로 소셜별로 데이터를 받는 데이터를 분기 처리하는 Dto
 */
@Getter
public class OAuth2AttributesDto {

    private String nameAttributeKey;        // OAuth2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
    private OAuth2UserInfo oAuth2UserInfo;  // 소셜 타입별 로그인 유저 정보(닉네임, 이메일 프로필 등)

    @Builder
    public OAuth2AttributesDto(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    /**
     * SocialType에 맞는 메소드 호출하여 OAUthAttributes 객체 반환한다. param: userNameAttributesName -> OAUth2 로그인
     * 시 키(pk가 되는 값, attributes : OAuth 서비스의 유저 정보들
     */
    public static OAuth2AttributesDto of(SocialType socialType, String userNameAttributeName,
            Map<String, Object> attributes) {
        if (socialType == SocialType.NAVER) {
            return ofNaver(userNameAttributeName, attributes);
        }
        if (socialType == SocialType.KAKAO) {
            return ofKakao(userNameAttributeName, attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuth2AttributesDto ofKakao(String userNameAttributeName,
            Map<String, Object> attributes) {
        return OAuth2AttributesDto.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuth2AttributesDto ofGoogle(String userNameAttributeName,
            Map<String, Object> attributes) {
        return OAuth2AttributesDto.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuth2AttributesDto ofNaver(String userNameAttributeName,
            Map<String, Object> attributes) {
        return OAuth2AttributesDto.builder()
                .nameAttributeKey(userNameAttributeName)
                .oAuth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }

    /**
     * of메소드로 OAuthAttributes 객체가 생성되어, 유저 정보들이 담긴 OAuth2UserInfo가 소셜 타입별로 주입된 상태 OAuth2UserInfo에서
     * socialId(식별값), nickname, imageUrl을 가져와서 build email에는 UUID로 중복 없는 랜덤 값 생성 role은 GUEST로 설정 이후에
     * CustomOAuth2UserService에서 Db에 저장할 내 서비스 User를 OAuth2UserInfo의 정보를 사용하여 빌더로 빌드 후 반환합니다.
     */
    public Users toEntity(SocialType socialType, OAuth2UserInfo oauth2UserInfo) {
        return Users.builder()
            .socialType(socialType)
            .socialId(oauth2UserInfo.getId())
            .email(oauth2UserInfo.getEmail())
            .name(oauth2UserInfo.getName())
//                .nickname(oauth2UserInfo.getNickname())
//                .imageUrl(oauth2UserInfo.getImageUrl())
            .role(Role.MEMBER)
            .build();
    }

}
