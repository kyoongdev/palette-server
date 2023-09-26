package com.study.palette.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDto {
    private final int status;
    private final String timestamp;
    private final String path;
    private final String message;
}
