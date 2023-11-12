package com.study.palette.module.musician.dto;

import com.study.palette.module.musician.entity.UserMusician;
import com.study.palette.module.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMusicianDto {
    private String nickName;
    private String name;
    private int groupType;
    private List<UserSnsRequestDto> snsAddress = new ArrayList<>();
    private List<Integer> positionType = new ArrayList<>();
    public UserMusician toEntity(User user) {
        return UserMusician.builder()
                .nickName(this.getNickName())
                .name(this.getName())
                .groupType(this.getGroupType())
                .userSns(new ArrayList<>())
                .userPosition(new ArrayList<>())
                .user(user)
                .build();
    }
}
