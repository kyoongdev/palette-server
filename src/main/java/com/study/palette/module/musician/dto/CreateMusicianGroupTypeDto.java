package com.study.palette.module.musician.dto;

import com.study.palette.common.enums.musician.MusicianGroupType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMusicianGroupTypeDto {

  @Schema(description = "종류", example = "1 : SOLO, 2 : GROUP")
  private MusicianGroupType musicianGroupType;

//  public


}
