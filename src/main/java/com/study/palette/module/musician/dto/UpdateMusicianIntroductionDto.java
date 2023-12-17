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
public class UpdateMusicianIntroductionDto {

  private String introduction;
}
