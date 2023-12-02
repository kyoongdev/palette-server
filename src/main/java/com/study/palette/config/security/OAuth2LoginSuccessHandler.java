package com.study.palette.config.security;

import com.study.palette.common.dto.TokenDto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

  @Value("${server.host.front}")
  private String frontServerHost;

  private final JwtTokenProvider jwtTokenProvider;


  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    TokenDto token = jwtTokenProvider.createToken(authentication);

    response.sendRedirect(
        frontServerHost + "/loginSuccess?accessToken=" + token.getAccessToken() + "&refreshToken="
            + token.getRefreshToken());

  }

}