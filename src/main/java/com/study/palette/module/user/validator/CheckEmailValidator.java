package com.study.palette.module.user.validator;

import com.study.palette.module.user.dto.CreateUserDto;
import com.study.palette.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<CreateUserDto> {

  private final UserRepository userRepository;

  @Override
  protected void doValidate(CreateUserDto dto, Errors errors) {
    if (userRepository.existsByEmail(dto.getEmail())) {
      errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
    }
  }
}
