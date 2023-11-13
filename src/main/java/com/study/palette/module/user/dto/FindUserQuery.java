package com.study.palette.module.user.dto;


import com.study.palette.common.dto.PageDto;
import lombok.Data;


//TODO: 필터 + 정렬 추가
@Data
public class FindUserQuery extends PageDto {

  private String name;
}
