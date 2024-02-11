package com.study.palette.module.musician.dto;

import static com.study.palette.module.musician.entity.QUsersMusician.usersMusician;

import com.study.palette.common.PaletteUtils;
import com.study.palette.module.musician.entity.UsersMusician;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMusicianProfileDto {

  private String profileImage;
  private String introduction;

  public UsersMusician toEntity(UsersMusician usersMusician) {


    PaletteUtils.myCopyProperties(this, usersMusician);

    return usersMusician;

  }
}
