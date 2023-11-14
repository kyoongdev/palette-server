package com.study.palette.module.users.validator;

import com.study.palette.module.users.dto.CreateUserDto;
import com.study.palette.module.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<CreateUserDto> {

  private final UsersRepository usersRepository;

  @Override
  protected void doValidate(CreateUserDto dto, Errors errors) {
    if (usersRepository.existsByEmail(dto.getEmail())) {
      errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
    }
  }
}
