package com.study.palette.module.user.controller;

import com.study.palette.module.user.dto.CreateUserDto;
import com.study.palette.module.user.dto.LoginRequest;
import com.study.palette.module.user.entity.User;
import com.study.palette.module.user.service.UserService;
import com.study.palette.module.user.validator.CheckEmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final CheckEmailValidator checkEmailValidator;

    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkEmailValidator);
    }

    @PostMapping("/signup")
    public String signup(@Valid @RequestBody CreateUserDto createUserDto, Errors errors, Model model
                       ){
        if(errors.hasErrors()){
            model.addAttribute("dto",createUserDto);

            Map<String ,String> validatorResult = userService.validationHandling(errors);
            for(String key:validatorResult.keySet()){
                model.addAttribute(key, validatorResult.get(key));
            }

            return "duplicated email";
        }


        UUID userId = userService.join(createUserDto);
        return userId.toString();
    }
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest
                      ){
        User user = userService.login(loginRequest);
        return user;
    }
}
