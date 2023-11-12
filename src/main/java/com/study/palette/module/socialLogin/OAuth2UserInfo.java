package com.study.palette.module.socialLogin;

import java.util.Map;

public abstract class OAuth2UserInfo {

    /* 추상클래스를 상속받는 클래스에서만 사용할 수 있도록 protected제어자 사용*/
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getImageUrl();

    public abstract String getEmail();


}
