package com.study.palette.module.socialLogin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.palette.module.socialLogin.service.SocialLoginService;
import com.study.palette.module.user.entity.SocialType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.net.URI;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "소셜 로그인", description = "소셜 로그인")
@RestController
@RequestMapping(value = "/api/social")
@Log4j2
public class SocialLoginController {

  private final SocialLoginService socialLoginService;


  @Autowired
  public SocialLoginController(SocialLoginService socialLoginService) {
    this.socialLoginService = socialLoginService;
  }

  @Operation(summary = "소셜 로그인", description = "소셜 로그인 입니다.")
  @GetMapping("/{socialLoginType}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "302", description = "로그인 폼", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  public ResponseEntity<?> getSocialLoginUrl(
      @PathVariable(name = "socialLoginType") String socialLoginPath, HttpServletResponse response)
      throws IOException {
    SocialType socialLoginType = SocialType.valueOf(socialLoginPath.toUpperCase());
    String redirectUrl = socialLoginService.redirectUrl(socialLoginType);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create(redirectUrl));

    return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);


  }

  @Operation(summary = "소셜 로그인", description = "소셜 로그인 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "302", description = "로그인 폼", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/login/oauth2/code/google")
  public String googleResult(@RequestParam(name = "code") String code)
      throws JsonProcessingException {

    SocialType socialType = SocialType.GOOGLE;

    log.info(code);

    return socialLoginService.socialLogin(code, socialType);

  }

  @Operation(summary = "소셜 로그인", description = "소셜 로그인 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "302", description = "로그인 폼", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/login/oauth2/code/kakao")
  public String kakaoResult(@RequestParam(name = "code") String code)
      throws JsonProcessingException {

    SocialType socialType = SocialType.KAKAO;

    log.info(code);

    return socialLoginService.socialLogin(code, socialType);

  }

  @Operation(summary = "소셜 로그인", description = "소셜 로그인 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "302", description = "로그인 폼", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/login/oauth2/code/naver")
  public String naverResult(@RequestParam(name = "code") String code)
      throws JsonProcessingException {

    SocialType socialType = SocialType.NAVER;

    log.info(code);

    return socialLoginService.socialLogin(code, socialType);

  }

}
