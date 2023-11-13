package com.study.palette.module.auth.controller;

import com.study.palette.common.dto.TokenDto;
import com.study.palette.module.auth.service.AuthService;
import com.study.palette.module.users.dto.CreateUserDto;
import com.study.palette.module.users.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "인증 API")
public class AuthController {

  public final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @Operation(summary = "회원가입", description = "회원가입을 합니다.")
  @PostMapping("join")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDto.class)))
  })
  public TokenDto join(@RequestBody CreateUserDto body) {
    return authService.join(body);
  }

  @Operation(summary = "로그인", description = "로그인을 합니다.")
  @PostMapping("login")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDto.class)))
  })
  public TokenDto login(@RequestBody LoginRequest body) {
    return authService.login(body);
  }

  @Operation(summary = "토큰 재발행", description = "토큰을 재발행합니다.")
  @PostMapping("refresh")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenDto.class)))
  })
  public TokenDto refresh(@RequestBody TokenDto body) {
    return authService.refresh(body);
  }
}
