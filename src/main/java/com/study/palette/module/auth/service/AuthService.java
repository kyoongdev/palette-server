package com.study.palette.module.auth.service;

import com.study.palette.common.dto.TokenDto;
import com.study.palette.config.security.JwtTokenProvider;
import com.study.palette.module.auth.exception.AuthErrorCode;
import com.study.palette.module.auth.exception.AuthException;
import com.study.palette.module.users.dto.CreateUserDto;
import com.study.palette.module.users.dto.LoginRequest;
import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Transactional
@Service
public class AuthService {

  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Autowired()
  public AuthService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
    this.usersRepository = usersRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
  }


  public TokenDto join(CreateUserDto createUserDto) {

    boolean isExist = usersRepository.existsByEmail(createUserDto.getEmail());

    if (isExist) {
      throw new AuthException(AuthErrorCode.EXISTS_USER_ERROR);
    }

    Users users = Users.builder()
        .role(Role.MEMBER)
        .email(createUserDto.getEmail())
        .password(passwordEncoder.encode(createUserDto.getPassword()))
        .name(createUserDto.getName())
        .phone(createUserDto.getPhone())
        .isAlarmAccept(createUserDto.isAlarmAccept())
        .build();

    String id = usersRepository.save(users).getId().toString();

    Authentication authentication = jwtTokenProvider.getAuthentication(id);

    return jwtTokenProvider.createToken(authentication);
  }


  public TokenDto login(LoginRequest loginRequest) {
    Optional<Users> optionalUser = usersRepository.findByEmail(loginRequest.getEmail());

    if (optionalUser.isEmpty()) {
      throw new AuthException(AuthErrorCode.USER_NOT_FOUND);
    }

    Users users = optionalUser.get();
    if (!passwordEncoder.matches(loginRequest.getPassword(), users.getPassword())) {
      throw new AuthException(AuthErrorCode.PASSWORD_BAD_REQUEST);
    }

    Authentication authentication = jwtTokenProvider.getAuthentication(users.getId().toString());

    return jwtTokenProvider.createToken(authentication);
  }

  public TokenDto refresh(TokenDto token) {
    Authentication refreshTokenAuthentication = jwtTokenProvider.getAuthentication(jwtTokenProvider.compareToken(token));

    // 저장된 Refresh Token 값을 가져옴
    Optional<Users> user = usersRepository.findById(
        UUID.fromString(refreshTokenAuthentication.getName()));
    if (user.isEmpty()) {
      throw new AuthException(AuthErrorCode.USER_NOT_FOUND);
    }
    // 토큰 재발행
    Authentication authentication = jwtTokenProvider.getAuthentication(user.get().getId().toString());

    return jwtTokenProvider.createToken(authentication);
  }

}
