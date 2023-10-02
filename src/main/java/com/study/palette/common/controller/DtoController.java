package com.study.palette.common.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;

import com.study.palette.common.exception.CustomException;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class DtoController {




    @GetMapping(value = "/test")
    public String test(@RequestParam(required = false) String param) throws Exception {

        if(param.equals("2")) {
            //ArithmeticException 발생
            int div = 1/0;
        }
        return "success";
    }

}
