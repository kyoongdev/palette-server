package com.study.palette.module.user.service;

import com.study.palette.module.user.dto.CreateUserDto;
import com.study.palette.module.user.dto.LoginRequest;
import com.study.palette.module.user.entity.Role;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UUID join(CreateUserDto createUserDto) {

        User user = User.builder()
                .role(Role.MEMBER)
                .email(createUserDto.getEmail())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .name(createUserDto.getName())
                .phone(createUserDto.getPhone())
                .isAlarmAccept(createUserDto.isAlarmAccept())
                .build();
        return userRepository.save(user).getId();
    }

    @Override
    public User login(@RequestBody LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

        if(optionalUser.isEmpty()){
            return null;
        }

        User user = optionalUser.get();
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())
        ){
            return null;
        }

        return user;
    }

    @Override
    public Map<String, String> validationHandling(Errors errors) {
        Map<String, String > validatorResult = new HashMap<>();

        for (FieldError error: errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }


}
