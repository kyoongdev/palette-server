package com.study.palette.module.user.dto;

import com.study.palette.common.PaletteUtils;
import com.study.palette.module.user.entity.User;
import lombok.*;

import java.util.UUID;

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
