package com.study.palette.authentication.application;

import com.study.palette.authentication.domain.JwtTokenProvider;
import com.study.palette.authentication.presentation.dto.TokenDto;
import com.study.palette.config.UserDetailsServiceImpl;
import com.study.palette.module.user.entity.RefreshToken;
import com.study.palette.module.user.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public AuthService(JwtTokenProvider jwtTokenProvider, RefreshTokenRepository refreshTokenRepository, UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public TokenDto reissueToken(TokenDto tokenDto) throws RuntimeException {// TODO 추후 예외 처리

        // 검증 및 비교 된 토큰을 통해 Authentication 객체를 받아옴
        Authentication refreshTokenAuthentication = jwtTokenProvider.getAuthentication(jwtTokenProvider.compareToken(tokenDto));

        // 저장된 Refresh Token 값을 가져옴
        Optional<RefreshToken> refreshTokenEntity = refreshTokenRepository.findByUserId(refreshTokenAuthentication.getName());
        String redisRefreshToken = refreshTokenEntity.get().getRefreshToken();
        if (!redisRefreshToken.equals(tokenDto.getRefreshToken())) {
            throw new RuntimeException(); // TODO 추후 예외 처리
        }
        // 토큰 재발행
        return jwtTokenProvider.createToken(refreshTokenAuthentication);
    }


}