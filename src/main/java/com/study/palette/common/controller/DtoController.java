package com.study.palette.common.controller;

import com.study.palette.common.constants.ErrorCode;
import com.study.palette.common.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1")
public class DtoController {




    @GetMapping(value = "/test")
    public String test(@RequestParam(required = false) String param) throws Exception {

        throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    }




}
