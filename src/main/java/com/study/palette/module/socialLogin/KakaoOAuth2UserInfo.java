package com.study.palette.module.socialLogin;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

  /* 카카오는 유저 정보가 kakao_account.profile로 두번 감싸져 있는 구조라 get을 2번 사용 후 원하는 정보의 key값을 꺼내서 사용해야한다.
   * 또한 getId는 Long타입으로 반환되어 String.ofValue 사용*/
  public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
    super(attributes);
  }

  @Override
  public String getId() {
    return String.valueOf(attributes.get("id"));
  }

  @Override
  public String getName() {

    Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

    if (account == null) {
      return null;
    }

    Map<String, Object> profile = (Map<String, Object>) account.get("profile");

    if (profile == null) {
      return null;
    }

    return (String) profile.get("nickname");
  }

  @Override
  public String getImageUrl() {

    Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
    Map<String, Object> profile = (Map<String, Object>) account.get("profile");

    if (account == null || profile == null) {
      return null;
    }

    return (String) profile.get("thumbnail_image_url");
  }

  @Override
  public String getEmail() {

    Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

    if (account == null) {
      return null;
    }

    String email = account.get("email").toString();

    return email;

  }
}
