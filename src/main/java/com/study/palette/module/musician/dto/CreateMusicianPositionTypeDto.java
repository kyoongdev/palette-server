package com.study.palette.module.musician.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMusicianPositionTypeDto {

  @Schema(description = "종류", example = "1 : 작곡가 2 : 아티스트 3 : 녹음실운영자 4 : 엔지니어 5 : 앨범 아트 예술가")
  private int type;

}
