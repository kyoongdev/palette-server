package com.study.palette.module.socialLogin;

import com.study.palette.module.user.entity.Role;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

/* OAuth2User는 OAuth2.0 표준 사용자를 나타내며 사용자의 이름, 이메일, 전화번호 같은 하나 이상의 속성으로 구성된다.
   이 때 공급자(naver, kakao, 구글 간에 표준화되지 않아 DefaultOAuth2User라는 표준을 만들어서 제공한다.*/
@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private String email;
    private Role role;

    /**
     * Constructs a {@code DefaultOAuth2User} using the provided parameters.
     *
     * @param authorities      the authorities granted to the user
     * @param attributes       the attributes about the user
     * @param nameAttributeKey the key used to access the user's &quot;name&quot; from
     *                         {@link #getAttributes()}
     */
    public CustomOAuth2User(
            Collection<? extends GrantedAuthority> authorities,
            Map<String, Object> attributes, String nameAttributeKey,
            String email, Role role) {
        super(authorities, attributes, nameAttributeKey);
        this.email = email;
        this.role = role;
    }
}
