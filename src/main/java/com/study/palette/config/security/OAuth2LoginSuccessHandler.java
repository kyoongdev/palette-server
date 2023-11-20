package com.study.palette.config.security;

import com.study.palette.common.dto.TokenDto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

  private final JwtTokenProvider jwtTokenProvider;


  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    log.info("authentication : " + authentication.getName());

    TokenDto token = jwtTokenProvider.createToken(authentication);

    log.info("token : " + token.getAccessToken());

    response.sendRedirect(
        "http://localhost:8080/login?accessToken=" + token.getAccessToken() + "&refreshToken="
            + token.getRefreshToken());

  }

}