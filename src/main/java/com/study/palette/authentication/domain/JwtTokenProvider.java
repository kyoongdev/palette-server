package com.study.palette.authentication.domain;

import com.study.palette.common.dto.TokenDto;
import com.study.palette.config.UserDetailsServiceImpl;
import com.study.palette.module.user.entity.RefreshToken;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.RefreshTokenRepository;
import com.study.palette.module.user.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;


@Component
@RequiredArgsConstructor
@Log4j2
public class JwtTokenProvider {

  private final RefreshTokenRepository refreshTokenRepository;
  private final UserRepository userRepository;
  private UserDetailsServiceImpl userDetailsService;
  @Value("${jwt.secret}")
  private String secretKey;
  @Value("${jwt.token.access-expiration-time}")
  private long accessExpirationTime;
  @Value("${jwt.token.refresh-expiration-time}")
  private long refreshExpirationTime;

  @Autowired
  public JwtTokenProvider(UserDetailsServiceImpl userDetailsService, RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
    this.userDetailsService = userDetailsService;
    this.refreshTokenRepository = refreshTokenRepository;
    this.userRepository = userRepository;
  }

  /**
   * 토큰 생성
   */
  public TokenDto createToken(Authentication authentication) {
    User user = userRepository.findById(UUID.fromString(authentication.getName()))
            .orElseThrow(() -> {
              return new RuntimeException("로그인에러");//TODO 추후 에러처리
            });

    Claims claims = Jwts.claims().setSubject(user.getId().toString());
    Date now = new Date();
    Date expireDate = new Date(now.getTime() + accessExpirationTime);

    String accessToken = Jwts.builder()
            .setClaims(claims)
            .claim("ROLE", user.getRole())
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();

    String refreshToken = Jwts.builder()
            .setClaims(claims)
            .claim("ROLE", user.getRole())
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();

    //TODO refresh token 저장 전에 기존 refresh token 삭제
    refreshTokenRepository.deleteByUserId(UUID.fromString(authentication.getName()));

    //refresh token 저장
    refreshTokenRepository.save(
            RefreshToken.builder()
                    .user(user)
                    .refreshToken(refreshToken)
                    .refreshExpirationTime(refreshExpirationTime)
                    .issuedAt(now)
                    .build()
    );

    return new TokenDto(accessToken, refreshToken);
  }

  /**
   * Access 토큰을 검증
   * 검증 완료시 클레임을 반환하는 메서드로 변경
   */
  public Claims validateToken(String token) {
    try {
      return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
//            log.error(EXPIRED_JWT.getMessage());
      throw new RuntimeException();// TODO 추후 예외 처리
    } catch (JwtException e) {
//            log.error(INVALID_JWT.getMessage());
      throw new RuntimeException();// TODO 추후 예외 처리
    }
  }

  /**
   * http 헤더로부터 bearer 토큰을 가져옴.
   */
  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  /**
   * Access 토큰과 Refresh 토큰 키값을 검증하고 키값을 반환한다
   */
  public String compareToken(TokenDto tokenDto) {
    String accessTokenSubject = validateToken(tokenDto.getAccessToken()).getSubject();
    String refreshTokenSubject = validateToken(tokenDto.getRefreshToken()).getSubject();

    if (accessTokenSubject.equals(refreshTokenSubject)) {
      return refreshTokenSubject;
    }
    return null;
  }

  /**
   * 토큰으로부터 클레임을 만들고, 이를 통해 User 객체 생성해 Authentication 객체 반환
   * 토큰 검증단게를 뛰어넘고 검증된 토큰의 클레임을 받는 메서드로 변경
   */
  public Authentication getAuthentication(String subject) throws RuntimeException {
    UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }
}