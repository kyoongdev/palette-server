package com.study.palette.common.exception;

import com.study.palette.common.dto.ErrorDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    private String getUri() {
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        int i = uri.indexOf('/', 7);
        return uri.substring(i);
    }


    @ExceptionHandler({CustomException.class})
    protected ResponseEntity handleCustomException(CustomException ex) {
        return new ResponseEntity(new ErrorDto(ex.getErrorCode().getStatus(), new Timestamp(System.currentTimeMillis()).toString(),
                getUri(), ex.getErrorCode().getMessage()),
                HttpStatus.valueOf(ex.getErrorCode().getStatus()));

    }

    //TODO 특정 예외메세지가 통일되는 이슈발생 상세 예외처리를 위해 주석 OJH
//    @ExceptionHandler({Exception.class})
//    protected ResponseEntity handleServerException(Exception ex) {
//        return new ResponseEntity(new ErrorDto(INTERNAL_SERVER_ERROR.getStatus(), new Timestamp(System.currentTimeMillis()).toString(),
//                getUri(), INTERNAL_SERVER_ERROR.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    //유효성 검사 예외처리 추가 OJH
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity(new ErrorDto(HttpStatus.BAD_REQUEST.value(), new Timestamp(System.currentTimeMillis()).toString(),
                getUri(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

}
