package com.study.palette.config.security;

import com.study.palette.module.socialLogin.CustomOAuth2User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAUth2LoginSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        String requsetUrl = request.getRequestURI();
        String queryString = request.getQueryString();

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

    }
}
