package com.study.palette.module.socialLogin.service;

import com.study.palette.module.socialLogin.dto.OAuth2AttributesDto;
import com.study.palette.module.users.entity.SocialType;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import com.study.palette.module.users.service.PrincipalDetails;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final String NAVER = "naver";
    private static final String KAKAO = "kakao";

    private final UsersRepository usersRepository;

    @Autowired
    public CustomOAuth2UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("로그인 Service 요청 시도");
        log.info("userRequest : " + userRequest);

        /**
         * DefaultOAuth2UserService 객체를 생성하여, loadUser(userRequest)를 통해 DefaultOAuth2User 객체를 생성 후 반환
         * DefaultOAuth2UserService의 loadUser()는 소셜 로그인 API의 사용자 정보 제공 URI로 요청을 보내서
         * 사용자 정보를 얻은 후, 이를 통해 DefaultOAuth2User 객체를 생성 후 반환한다.
         * 결과적으로, OAuth2User는 OAuth 서비스에서 가져온 유저 정보를 담고 있는 유저
         */
        OAuth2User oAuth2User = super.loadUser(userRequest);

        /**
         * userRequest에서 registrationId 추출 후 registrationId으로 SocialType 저장
         * http://localhost:8080/oauth2/authorization/kakao에서 kakao가 registrationId
         * userNameAttributeName은 이후에 nameAttributeKey로 설정된다.
         */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = getSocialType(registrationId);
        String userNameAttributeName = userRequest.getClientRegistration()
            .getProviderDetails().getUserInfoEndpoint()
            .getUserNameAttributeName(); // OAuth2 로그인 시 키(PK)가 되는 값
        Map<String, Object> attributes = oAuth2User.getAttributes(); // 소셜 로그인에서 API가 제공하는 userInfo의 Json 값(유저 정보들)

        log.info("registrationId : " + registrationId);

        // socialType에 따라 유저 정보를 통해 OAuthAttributes 객체 생성
        OAuth2AttributesDto extractAttributes = OAuth2AttributesDto.of(socialType,
            userNameAttributeName, attributes);

        log.info("extractAttributes : " + extractAttributes);

        Users createdUsers = getUser(extractAttributes,
            socialType); // getUser() 메소드로 User 객체 생성 후 반환

        Map<String, Object> userId = new HashMap<>();
        userId.put("id", createdUsers.getId().toString());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add((GrantedAuthority) () -> createdUsers.getRole().getKey());

        return new PrincipalDetails(userId, grantedAuthorities);
    }

    private SocialType getSocialType(String registrationId) {
        if (NAVER.equals(registrationId)) {
            return SocialType.NAVER;
        }
        if (KAKAO.equals(registrationId)) {
            return SocialType.KAKAO;
        }
        return SocialType.GOOGLE;
    }

    /**
     * SocialType과 attributes에 들어있는 소셜 로그인의 식별값 id를 통해 회원을 찾아 반환하는 메소드 만약 찾은 회원이 있다면, 그대로 반환하고 없다면
     * saveUser()를 호출하여 회원을 저장한다.
     */
    private Users getUser(OAuth2AttributesDto attributes, SocialType socialType) {
        Users findUsers = usersRepository.findBySocialTypeAndSocialId(socialType,
            attributes.getOAuth2UserInfo().getId()).orElse(null);

        if (findUsers == null) {
            return saveUser(attributes, socialType);
        }
        return findUsers;
    }

    /**
     * OAuthAttributes의 toEntity() 메소드를 통해 빌더로 User 객체 생성 후 반환 생성된 User 객체를 DB에 저장 : socialType,
     * socialId, email, role 값만 있는 상태
     */
    private Users saveUser(OAuth2AttributesDto attributes, SocialType socialType) {
        Users createdUsers = attributes.toEntity(socialType, attributes.getOAuth2UserInfo());
        return usersRepository.save(createdUsers);
    }
}
