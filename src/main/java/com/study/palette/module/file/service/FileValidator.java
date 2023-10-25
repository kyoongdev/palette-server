package com.study.palette.module.file.service;

import com.study.palette.module.file.annotation.FileType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<FileType, MultipartFile> {
    private String allowedType;
    private boolean isAllow;

    @Override
    public void initialize(FileType constraintAnnotation) {
        this.allowedType = constraintAnnotation.value().toLowerCase();
        this.isAllow = constraintAnnotation.isAllow();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true; // 비어있으면 패스
        }

        String contentType = file.getContentType();
        if (isAllow) {
            return contentType != null && contentType.toLowerCase().startsWith(allowedType);
        } else {
            return contentType != null && !contentType.toLowerCase().startsWith(allowedType);
        }
    }
}
