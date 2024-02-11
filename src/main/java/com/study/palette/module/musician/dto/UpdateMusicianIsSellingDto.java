package com.study.palette.module.musician.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMusicianIsSellingDto {

  @Schema(description = "판매 상태", example = "true")
  private boolean isSelling;

  @Schema(description = "시장명", example = "1 MrBeat, 2 Artist, 3 Recording, 4 MixMastering, 5 AlbumArt")
  private int serviceType;

}
