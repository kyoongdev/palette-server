package com.study.palette.module.address.dto;

import com.study.palette.common.enums.recording.CityCode;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponseDto {

  private List<CityCode> cityCodes;

  public CityResponseDto(CityRequestDto cityRequestDto) {
    this.cityCodes = CityCode.getCityCodes(cityRequestDto.getRegionCode());
  }
}
