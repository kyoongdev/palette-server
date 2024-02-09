package com.study.palette;

import com.study.palette.config.security.JwtTokenProvider;
import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class TokenGenerator implements CommandLineRunner {

  private final UsersRepository usersRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;


  @Autowired
  public TokenGenerator(UsersRepository usersRepository, JwtTokenProvider jwtTokenProvider,
      PasswordEncoder passwordEncoder) {
    this.usersRepository = usersRepository;
    this.jwtTokenProvider = jwtTokenProvider;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args) throws Exception {
    Users user = usersRepository.findByEmail("testEmail").orElse(null);
    if (user != null) {
      log.info("이미 더미데이터가 존재합니다.");
      Authentication authentication = jwtTokenProvider.getAuthentication(user.getId().toString());
      log.info("MUSICIAN : \n" + jwtTokenProvider.createToken(authentication).getAccessToken());

      Users adminUser = usersRepository.findByEmail("adminEmail").orElse(null);
      Authentication adminAuthentication = jwtTokenProvider.getAuthentication(adminUser.getId().toString());
      log.info("ADMIN : \n" + jwtTokenProvider.createToken(adminAuthentication).getAccessToken());
      return;
    }
    // 여기에 초기 데이터 생성 로직을 작성
    Users musicianUsers = Users.builder()
        .email("testEmail")
        .password(passwordEncoder.encode("testPassword"))
        .role(Role.MUSICIAN)
        .name("testName")
        .phone("testPhone")
        .isAlarmAccept(true)
        .loginFailCount(0)
        .isLocked(false)
        .build();
    usersRepository.save(musicianUsers);

    Users adminUsers = Users.builder()
        .email("adminEmail")
        .password(passwordEncoder.encode("testPassword"))
        .role(Role.ADMIN)
        .name("adminName")
        .phone("adminPhone")
        .isAlarmAccept(true)
        .loginFailCount(0)
        .isLocked(false)
        .build();
    usersRepository.save(adminUsers);

    Authentication musicianAuthentication = jwtTokenProvider.getAuthentication(musicianUsers.getId().toString());
    Authentication adminAuthentication = jwtTokenProvider.getAuthentication(adminUsers.getId().toString());

    log.info("MUSICIAN : \n" + jwtTokenProvider.createToken(musicianAuthentication).getAccessToken());
    log.info("ADMIN : \n" + jwtTokenProvider.createToken(adminAuthentication).getAccessToken());

  }
}