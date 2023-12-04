package com.study.palette.module.socialLogin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "소셜 로그인", description = "소셜 로그인")
@RestController
@Log4j2
public class SocialLoginController {

  @Operation(summary = "네이버 소셜 로그인", description = "네이버 소셜 로그인 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "302", description = "로그인 폼", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/social/naver")
  public void naverSocialLogin(HttpServletResponse response) throws IOException {

    response.sendRedirect("/oauth2/authorization/naver");

  }

  @Operation(summary = "구글 소셜 로그인", description = "구글 소셜 로그인 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "302", description = "로그인 폼", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/social/google")
  public void googleSocialLogin(HttpServletResponse response) throws IOException {

    response.sendRedirect("/oauth2/authorization/google");

  }

  @Operation(summary = "카카오 소셜 로그인", description = "카카오 소셜 로그인 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "302", description = "로그인 폼", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/social/kakao")
  public void getSocialLoginUrl(HttpServletResponse response) throws IOException {

    response.sendRedirect("/oauth2/authorization/kakao");

  }

  @Operation(summary = "소셜로그인 결과", description = "소셜로그인 결과 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "302", description = "로그인 폼", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/login")
  public void redirectSocialResponse(HttpServletResponse response) throws IOException {

  }


}
