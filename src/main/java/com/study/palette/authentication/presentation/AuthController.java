package com.study.palette.authentication.presentation;

import com.study.palette.authentication.application.AuthService;
import com.study.palette.authentication.presentation.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/reissue")
    public TokenDto reissue(TokenDto tokenDto){
        return authService.reissueToken(tokenDto);// TODO 추후 예외 처리
    }
}