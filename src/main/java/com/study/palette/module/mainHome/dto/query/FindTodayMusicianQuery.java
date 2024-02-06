package com.study.palette.module.mainHome.dto.query;

import com.study.palette.common.dto.PageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindTodayMusicianQuery extends PageDto {
  @Schema(description = "서비스타입", example = "1")
  private String serviceType;
}
