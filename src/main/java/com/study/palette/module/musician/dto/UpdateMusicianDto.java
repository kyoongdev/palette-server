package com.study.palette.module.musician.dto;

import com.study.palette.module.musician.entity.UsersMusician;
import com.study.palette.module.users.entity.Users;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMusicianDto {

    private String stageName;
    private String name;
    private int groupType;
    private List<UserSnsRequestDto> snsAddress = new ArrayList<>();
    private List<Integer> positionType = new ArrayList<>();

//    public UsersMusician toEntity(Users user) {
//        return UsersMusician.builder()
//                .stageName(this.getStageName())
//                .name(this.getName())
//                .groupType(this.getGroupType())
//                .usersSns(new ArrayList<>())
//                .usersPosition(new ArrayList<>())
//                .users(user)
//                .build();
//    }
}
