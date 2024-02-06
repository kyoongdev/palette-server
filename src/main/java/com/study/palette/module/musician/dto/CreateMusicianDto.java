package com.study.palette.module.musician.dto;

import com.study.palette.common.enums.musician.MusicianAuthorizedType;
import com.study.palette.common.enums.musician.MusicianGroupType;
import com.study.palette.module.musician.entity.UsersMusician;
import com.study.palette.module.musician.entity.UsersMusicianAccount;
import com.study.palette.module.musician.entity.UsersMusicianFile;
import com.study.palette.module.musician.entity.UsersMusicianPosition;
import com.study.palette.module.musician.entity.UsersMusicianSns;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMusicianDto {

  @Schema(description = "활동명", example = "활동명")
  private String stageName;

  @Schema(description = "본명", example = "본명")
  private String name;

  @Schema(description = "활동 인원", example = "1 : 솔로 2 : 그룹")
  private int groupType;

  @Schema(description = "활동 인원", example = "1 : 승인대기 2 : 승인 3 : 반려")
  private int isAuthorized;

  private List<MusicianSnsRequestDto> snsAddress;

  private List<CreateMusicianPositionTypeDto> positionType;

  private CreateMusicianFileDto createMusicianFileDto;

  private CreateMusicianAccountDto createMusicianAccountDto;

  public UsersMusician toEntity(Users users) {
    UsersMusician usersMusician = UsersMusician.builder()
        .stageName(this.getStageName())
        .name(this.getName())
        .groupType(MusicianGroupType.findMusicianGroupType(this.getGroupType()))
        .isAuthorized(MusicianAuthorizedType.findMusicianAuthorizedType(this.getIsAuthorized()))
        .usersMusicianSns(new ArrayList<>())
        .usersMusicianPosition(new ArrayList<>())
        .usersMusicianFile(new UsersMusicianFile())
        .usersMusicianAccount(new UsersMusicianAccount())
        .users(users)
        .build();

    usersMusician.setUsersMusicianAccount(this.getCreateMusicianAccountDto().toEntity(
        usersMusician));

    usersMusician.setUsersMusicianSns(this.getSnsAddress().stream()
        .map(userSnsList -> UsersMusicianSns.from(userSnsList, usersMusician))
        .toList());

    usersMusician.setUsersMusicianPosition(this.getPositionType().stream()
        .map(
            musicianPositionList -> UsersMusicianPosition.from(musicianPositionList, usersMusician))
        .toList());

    usersMusician.setUsersMusicianFile((this.getCreateMusicianFileDto().toEntity(usersMusician)));

    return usersMusician;
  }
}
