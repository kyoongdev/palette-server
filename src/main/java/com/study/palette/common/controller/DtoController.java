package com.study.palette.common.controller;

import com.study.palette.common.constants.ErrorCode;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;

import com.study.palette.common.exception.CustomException;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class DtoController {

    @GetMapping("")
    public PaginationDto<String> test(){
        PagingDto pagingDto = new PagingDto(2,1,10,0,false,true);
        String[] array = {"안녕","ㅇㅇ"};
        return PaginationDto.of(pagingDto,array);
    }


    @GetMapping(value = "/test")
    public String test(@RequestParam(required = false) String param) throws Exception {

        throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    }




}
