package com.study.palette.common.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.PagingDto;
import com.study.palette.common.exception.CustomException;
import org.springframework.web.bind.annotation.*;

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

        if(param.equals("2")) {
            //ArithmeticException 발생
            int div = 1/0;
        }
        return "success";
    }
}
