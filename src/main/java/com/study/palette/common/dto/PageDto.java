package com.study.palette.common.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class PageDto {
    @Schema(description = "페이지", defaultValue = "1")
    int page;


    @Schema(description = "한 페이지에 보여줄 데이터 개수",defaultValue = "10")
    int limit;

    public Pageable toPageable(Sort sort){

        return PageRequest.of(page-1,limit,sort);
    }

}
