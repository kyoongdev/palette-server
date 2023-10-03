package com.study.palette.config;

import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws RuntimeException {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> {
                    return new RuntimeException("로그인에러");//TODO 추후 에러처리
                });

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        return new org
                .springframework
                .security
                .core
                .userdetails
                .User(user.getId().toString(), user.getPassword(), grantedAuthorities);
    }

}