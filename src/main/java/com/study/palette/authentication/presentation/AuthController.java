package com.study.palette.authentication.presentation;

import com.study.palette.authentication.application.AuthService;
import com.study.palette.authentication.domain.JwtTokenProvider;
import com.study.palette.authentication.presentation.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/reissue")
    public TokenDto reissue(TokenDto tokenDto) {
        return authService.reissueToken(tokenDto);// TODO 추후 예외 처리
    }
}