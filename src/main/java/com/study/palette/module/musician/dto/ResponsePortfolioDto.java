package com.study.palette.module.musician.dto;

import com.study.palette.module.musician.entity.UsersMusician;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsePortfolioDto {

  private String id;
  private String stageName;

  private String name;
  private String introduction;

  public static ResponsePortfolioDto toEntity(UsersMusician usersMusician) {
    return ResponsePortfolioDto.builder()
        .id(usersMusician.getId().toString())
        .stageName(usersMusician.getStageName())
        .name(usersMusician.getName())
        .introduction(usersMusician.getIntroduction())
        .build();
  }

}
