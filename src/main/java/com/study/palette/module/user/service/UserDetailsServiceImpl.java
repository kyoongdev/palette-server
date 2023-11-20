package com.study.palette.module.user.service;

import com.study.palette.module.users.entity.Users;
import com.study.palette.module.users.repository.UsersRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UsersRepository usersRepository;

  @Autowired
  public UserDetailsServiceImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String id) throws RuntimeException {
    Users users = usersRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> {
          return new RuntimeException("로그인에러");//TODO 추후 에러처리
        });

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add((GrantedAuthority) () -> users.getRole().getKey());

    return new PrincipalDetails(users.getId().toString(), grantedAuthorities);
  }
}