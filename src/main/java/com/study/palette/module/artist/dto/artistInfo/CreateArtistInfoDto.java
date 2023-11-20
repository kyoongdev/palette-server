package com.study.palette.module.artist.dto.artistInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateArtistInfoDto {

  private int licenseType;

  private int price;

  private String serveFile;

  private int updateCount;

  private int period;

  private int draftCount;

  private boolean isAssign;

  private boolean isUseCommerical;

  private boolean isServeOriginFile;


}
