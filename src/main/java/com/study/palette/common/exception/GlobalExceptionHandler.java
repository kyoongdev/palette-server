package com.study.palette.common.exception;

import com.study.palette.common.dto.ErrorDto;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

import static com.study.palette.common.constants.ErrorCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({CustomException.class})
    protected ResponseEntity handleCustomException(CustomException ex){
        return new ResponseEntity(new ErrorDto(ex.getErrorCode().getStatus(), new Timestamp(System.currentTimeMillis()).toString(),
                ServletUriComponentsBuilder.fromCurrentRequestUri().toString(),ex.getErrorCode().getMessage()),
                HttpStatus.valueOf(ex.getErrorCode().getStatus()));

    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleServerException(Exception ex){
        return new ResponseEntity(new ErrorDto(INTERNAL_SERVER_ERROR.getStatus(),new Timestamp(System.currentTimeMillis()).toString(),
                ServletUriComponentsBuilder.fromCurrentRequestUri().toString(),INTERNAL_SERVER_ERROR.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
