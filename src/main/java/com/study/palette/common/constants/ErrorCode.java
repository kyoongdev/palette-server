package com.study.palette.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 BAD_REQUEST 잘못된 요청
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요."),

    //404 NOT_FOUND 잘못된 리소스 접근
    NOT_FOUND(404, "잘못된 리소스 접근입니다."),

    //409 CONFLICT 중복된 리소스
    ALREADY_SAVED(409, "이미 저장된 리소스입니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500,"서버 에러입니다.");

    private final int status;
    private final String message;

}
