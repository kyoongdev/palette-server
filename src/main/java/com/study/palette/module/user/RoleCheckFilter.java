package com.study.palette.module.user;


import com.study.palette.module.user.dto.MyInfoResponseDto;
import com.study.palette.module.user.dto.UserProfileDto;
import com.study.palette.module.user.entity.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class RoleCheckFilter extends OncePerRequestFilter {
    private final UserService userService;

    public RoleCheckFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasAnyRole = Arrays.asList(Role.values()).stream().anyMatch(role ->
                authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals(role.name()))
        );

        if (hasAnyRole) {
            request.setAttribute("user", userService.getUserByIdWithDto(authentication.getName()));
        } else {
            request.setAttribute("user", MyInfoResponseDto.builder()
                    .userProfileDto(UserProfileDto.builder()
                            .id("test")
                            .email("testemail")
                            .build())
                    .build());
        }

        filterChain.doFilter(request, response);
    }
}
