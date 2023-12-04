package com.study.palette.module.address.controller;

import com.study.palette.module.address.dto.CityRequestDto;
import com.study.palette.module.address.dto.CityResponseDto;
import com.study.palette.module.address.dto.RegionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Address", description = "주소 관련 API")
@RestController
@RequestMapping("/api/address")
public class AddressController {

  /*지역목록 조회*/
  @Operation(summary = "지역 목록 조회", description = "지역 목록 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RegionResponseDto.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/regions")
  public RegionResponseDto getRegions() {
    return new RegionResponseDto();
  }

  /*도시목록 조회*/
  @Operation(summary = "도시 목록 조회", description = "도시 목록 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CityResponseDto.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/cities")
  public CityResponseDto getCities(CityRequestDto cityRequestDto) {
    return new CityResponseDto(cityRequestDto);
  }
}
