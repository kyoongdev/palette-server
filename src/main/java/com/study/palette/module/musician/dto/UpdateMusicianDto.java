package com.study.palette.module.musician.dto;

import com.study.palette.common.enums.musician.MusicianGroupType;
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
  private List<MusicianSnsRequestDto> snsAddress = new ArrayList<>();
  private List<Integer> positionType = new ArrayList<>();

  public UsersMusician toEntity(Users user) {
    return UsersMusician.builder()
        .stageName(this.getStageName())
        .name(this.getName())
        .groupType(MusicianGroupType.findMusicianGroupType(this.getGroupType()))
        .usersMusicianSns(new ArrayList<>())
        .usersMusicianPosition(new ArrayList<>())
        .users(user)
        .build();
  }
}
