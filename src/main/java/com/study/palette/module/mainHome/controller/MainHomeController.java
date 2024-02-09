package com.study.palette.module.mainHome.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.mainHome.dto.TodayMusiciansResponseDto;
import com.study.palette.module.mainHome.dto.TopMusiciansResponseDto;
import com.study.palette.module.mainHome.dto.query.FindTodayMusicianQuery;
import com.study.palette.module.mainHome.dto.query.FindTopMusicianQuery;
import com.study.palette.module.mainHome.service.MainHomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Main Home", description = "Main Home API")
@RequestMapping("/home")
@RestController
public class MainHomeController {

  private final MainHomeService mainHomeService;

  public MainHomeController(MainHomeService mainHomeService) {
    this.mainHomeService = mainHomeService;
  }

  //TOP 아티스트
  @Operation(summary = "모든 서비스 TOP 아티스트 조회", description = "모든 서비스에서 TOP 아티스트를 조회합니다.")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TopMusiciansResponseDto.class)))), @ApiResponse(responseCode = "400", description = "Bad Request")})
  @GetMapping("/top")
  public TopMusiciansResponseDto serviceInfosCount(FindTopMusicianQuery query) {
    return mainHomeService.getTopArtists(query);
  }

  //오늘의 아티스트
  @Operation(summary = "모든 서비스 오늘의 아티스트 조회", description = "모든 서비스에서 오늘의 아티스트를 조회합니다.")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TodayMusiciansResponseDto.class)))), @ApiResponse(responseCode = "400", description = "Bad Request")})
  @GetMapping("/today")
  public PaginationDto<TodayMusiciansResponseDto> todayArtistInfos(FindTodayMusicianQuery query) {
    return mainHomeService.getTodayArtists(query, query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));
  }

}

