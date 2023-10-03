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

    UserProfileDto userProfileDto;

    // TODO 서비스 구현시 추가
//    MusicianProfileDto musicianProfileDto;

    public User toUserEntity() {
        User user = new User();
        PaletteUtils.myCopyProperties(this.userProfileDto, user);
        return user;
    }

    //TODO 서비스 구현시 추가
//    public Musician toMusicianEntity() {
//        Musician musician = new Musician();
//        PaletteUtils.myCopyProperties(this.musicianProfileDto, musician);
//        return musician;
//    }
}
