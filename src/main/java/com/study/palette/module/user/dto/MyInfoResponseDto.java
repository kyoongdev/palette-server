package com.study.palette.module.user.dto;

import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class MyInfoResponseDto {

  User user;

  // TODO 서비스 구현시 추가
//    Musician musician;

}
