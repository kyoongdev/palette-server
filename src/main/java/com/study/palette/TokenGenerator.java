package com.study.palette;

import com.study.palette.config.security.JwtTokenProvider;
import com.study.palette.module.users.entity.Role;
import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import java.util.ArrayList;
import java.util.List;
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
      String musicianToken = jwtTokenProvider.createToken(jwtTokenProvider.getAuthentication(user.getId().toString())).getAccessToken();
      Users adminUser = usersRepository.findByEmail("adminEmail").orElse(null);
      String adminToken = jwtTokenProvider.createToken(jwtTokenProvider.getAuthentication(adminUser.getId().toString())).getAccessToken();

      log.info("MUSICIAN : \n" + musicianToken);
      log.info("ADMIN : \n" + adminToken);
      return;
    }
    // 여기에 초기 데이터 생성 로직을 작성
    List<Users> users = new ArrayList<>();

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
    users.add(musicianUsers);

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
    users.add(adminUsers);

    usersRepository.saveAll(users);

    Authentication musicianAuthentication = jwtTokenProvider.getAuthentication(musicianUsers.getId().toString());
    Authentication adminAuthentication = jwtTokenProvider.getAuthentication(adminUsers.getId().toString());

//    String musicianToken = jwtTokenProvider.createToken(musicianAuthentication).getAccessToken();
//    String adminToken = jwtTokenProvider.createToken(adminAuthentication).getAccessToken();

//    log.info("MUSICIAN : \n" + musicianToken);
//    log.info("ADMIN : \n" + adminToken);
  }
}