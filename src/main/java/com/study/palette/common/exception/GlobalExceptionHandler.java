package com.study.palette.common.exception;

import com.study.palette.common.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;

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

  @ExceptionHandler({Exception.class})
  protected ResponseEntity handleServerException(Exception ex) {
    return new ResponseEntity(new ErrorDto(INTERNAL_SERVER_ERROR.getStatus(), new Timestamp(System.currentTimeMillis()).toString(),
            getUri(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  protected ResponseEntity handleServerException(ConstraintViolationException ex) {
    return new ResponseEntity(new ErrorDto(INTERNAL_SERVER_ERROR.getStatus(), new Timestamp(System.currentTimeMillis()).toString(),
            getUri(), ex.getMessage()),
            HttpStatus.valueOf(INTERNAL_SERVER_ERROR.getStatus()));
  }


}
