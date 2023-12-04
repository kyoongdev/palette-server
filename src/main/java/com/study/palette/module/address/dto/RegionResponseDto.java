package com.study.palette.module.address.dto;

import com.study.palette.common.enums.recording.RegionCode;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionResponseDto {

  private List<RegionCode> regionCodes;

  public RegionResponseDto() {
    this.regionCodes = List.of(RegionCode.values());
  }
}
