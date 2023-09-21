package com.study.palette.module.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyInfoResponseDto {

    UserProfileDto userProfileDto;

    // TODO 서비스 구현시 추가
//    MusicianProfileDto musicianProfileDto;

}
