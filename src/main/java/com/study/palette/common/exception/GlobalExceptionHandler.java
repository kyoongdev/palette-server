package com.study.palette.common.exception;

import com.study.palette.common.dto.ErrorDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.stream.Stream;

import static com.study.palette.common.constants.ErrorCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {


    private String getUri(){
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        int i = uri.indexOf('/',7);
        return uri.substring(i);
    }

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity handleCustomException(CustomException ex){
        return new ResponseEntity(new ErrorDto(ex.getErrorCode().getStatus(), new Timestamp(System.currentTimeMillis()).toString(),
                getUri(),ex.getErrorCode().getMessage()),
                HttpStatus.valueOf(ex.getErrorCode().getStatus()));

    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleServerException(Exception ex){
        return new ResponseEntity(new ErrorDto(INTERNAL_SERVER_ERROR.getStatus(),new Timestamp(System.currentTimeMillis()).toString(),
                getUri(),INTERNAL_SERVER_ERROR.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
