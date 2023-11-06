package com.study.palette.module.socialLogin;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {


    /* google은 네이버와 카카오와 달리 유저 정보가 다른 키 값 안에 감싸져 있지 않기 때문에 바로 꺼내서 쓸 수 있다 */
    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
}
