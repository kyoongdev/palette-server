package com.study.palette.module.auth.service;

import com.study.palette.authentication.domain.JwtTokenProvider;
import com.study.palette.common.dto.TokenDto;
import com.study.palette.module.auth.exception.AuthErrorCode;
import com.study.palette.module.auth.exception.AuthException;
import com.study.palette.module.user.dto.CreateUserDto;
import com.study.palette.module.user.dto.LoginRequest;
import com.study.palette.module.user.entity.Role;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Optional;


@Transactional
@Service
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Autowired()
  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
  }


  public TokenDto join(CreateUserDto createUserDto) {

    boolean isExist = userRepository.existsByEmail(createUserDto.getEmail());

    if (isExist) {
      throw new AuthException(AuthErrorCode.EXISTS_USER_ERROR);
    }

    User user = User.builder()
            .role(Role.MEMBER)
            .email(createUserDto.getEmail())
            .password(passwordEncoder.encode(createUserDto.getPassword()))
            .name(createUserDto.getName())
            .phone(createUserDto.getPhone())
            .isAlarmAccept(createUserDto.isAlarmAccept())
            .build();

    String id = userRepository.save(user).getId();

    Authentication authentication = jwtTokenProvider.getAuthentication(id);

    return jwtTokenProvider.createToken(authentication);
  }


  public TokenDto login(@RequestBody LoginRequest loginRequest) {
    Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

    if (optionalUser.isEmpty()) {
      throw new AuthException(AuthErrorCode.USER_NOT_FOUND);
    }

    User user = optionalUser.get();
    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      throw new AuthException(AuthErrorCode.PASSWORD_BAD_REQUEST);
    }

    Authentication authentication = jwtTokenProvider.getAuthentication(user.getId());

    return jwtTokenProvider.createToken(authentication);
  }


}
