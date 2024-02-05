package com.study.palette.module.musician.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusicianSnsRequestDto {

  @Schema(description = "종류", example = "1 : 인스타그램, 2 : 트위터, 3 : 페이스북, 4 : 유튜브, 5 : 기타")
  private int type;

  @Schema(description = "내용")
  private String musicianSns;

}
