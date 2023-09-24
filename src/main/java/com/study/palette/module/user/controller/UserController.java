package com.study.palette.module.user.controller;

import com.study.palette.module.user.dto.CreateUserDto;
import com.study.palette.module.user.dto.LoginRequest;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public UUID signup(@Valid @RequestBody CreateUserDto createUserDto
                         ){
        UUID userId = userService.join(createUserDto);
        return userId;
    }
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest
                      ){
        User user = userService.login(loginRequest);
        return user;
    }
}
