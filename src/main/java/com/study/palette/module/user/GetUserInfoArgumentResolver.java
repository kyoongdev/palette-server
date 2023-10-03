package com.study.palette.module.user;

import com.study.palette.module.user.dto.MyInfoResponseDto;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class GetUserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    public GetUserInfoArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 호출된 메서드 정보 검증 MyInfoResponseDto.class, GetUserInfo.class 인지 확인
        return parameter.getParameterType().isAssignableFrom(MyInfoResponseDto.class)
                && parameter.hasParameterAnnotation(GetUserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //MyInfoResponseDto 타입으로 반환
        return userService.getUserByIdWithDto(authentication.getName());
    }

}