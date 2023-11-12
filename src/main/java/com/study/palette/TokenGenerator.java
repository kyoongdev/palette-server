package com.study.palette;

import com.study.palette.config.security.JwtTokenProvider;
import com.study.palette.module.user.entity.Role;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class TokenGenerator implements CommandLineRunner {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;


  @Autowired
  public TokenGenerator(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.jwtTokenProvider = jwtTokenProvider;
    this.passwordEncoder = passwordEncoder;
  }

    @Override
    public void run(String... args) throws Exception {
        // 여기에 초기 데이터 생성 로직을 작성
        User data = User.builder()
                .email("testEmail")
                .password(passwordEncoder.encode("testPassword"))
                .role(Role.MUSICIAN)
                .name("testName")
                .phone("testPhone")
                .isAlarmAccept(true)
                .loginFailCount(0)
                .isLocked(false)
                .build();
        userRepository.save(data);

    Authentication authentication = jwtTokenProvider.getAuthentication(data.getId().toString());
    log.info("\n" + jwtTokenProvider.createToken(authentication).getAccessToken());

  }
}