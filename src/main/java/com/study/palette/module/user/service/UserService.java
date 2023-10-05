package com.study.palette.module.user.service;

import com.study.palette.module.user.dto.CreateUserDto;
import com.study.palette.module.user.dto.LoginRequest;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import org.springframework.validation.Errors;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserService {


    UUID join(CreateUserDto createUserDto);
    User login(LoginRequest loginRequest);
    Map<String,String> validationHandling(Errors errors);

}
