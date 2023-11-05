package com.study.palette.module.file.annotation;

import com.study.palette.module.file.service.FileValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
public @interface FileType {

  String value() default "없음";

  String message() default "유효하지 않은 타입입니다";

  boolean isAllow() default true;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}