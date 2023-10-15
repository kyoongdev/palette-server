package com.study.palette.module.artist.controller;

import com.study.palette.common.dto.PageDto;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.artist.dto.ArtistDetailResponseDto;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.dto.CreateArtistDto;
import com.study.palette.module.artist.dto.UpdateArtistDto;
import com.study.palette.module.artist.dto.query.FindArtistsQuery;
import com.study.palette.module.artist.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "아티스트", description = "아티스트")
@RequestMapping("/users")
@RestController
public class ArtistController {

  private ArtistService artistService;

  @Autowired
  public ArtistController(ArtistService artistService) {
    this.artistService = artistService;
  }


  @Operation(summary = "아티스트 조회", description = "아티스트 조회 메서드입니다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/artist")
  //ResponseEntity -> PaginationDto
  //만들어둔 페이지네이션 객체 사용
  public PaginationDto<ArtistResponseDto> artistInfo(@ParameterObject PageDto query) {
    PaginationDto<ArtistResponseDto> artistInfo = artistService.findArtists(query.toPageable(Sort.by("serviceName")));

    return artistInfo;

  }

  @Operation(summary = "아티스트 조회", description = "아티스트 카테고리 필터별 조회 메서드입니다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/artist/category")
  public PaginationDto<ArtistResponseDto> artistInfoFilter(@ParameterObject FindArtistsQuery query) {
    PaginationDto<ArtistResponseDto> artistInfo = artistService.findArtistsFilter(query);


    return artistInfo;

  }

  @Operation(summary = "아티스트 상세", description = "아티스트 상세 조회.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/artist/detail")
  public ResponseEntity<ArtistDetailResponseDto> artistDetail(@ParameterObject String id) {

    ArtistDetailResponseDto data = artistService.findArtistsDetail(id);

    return ResponseEntity.ok().body(data);
  }



  @Operation(summary = "아티스트 생성", description = "아티스트 생성 메서드입니다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(mediaType = "application/json")),

  })
  @PostMapping(path = "/artist/save")
  public ResponseWithIdDto createArtist(@RequestBody() CreateArtistDto body) {

    ResponseWithIdDto result = artistService.createArtist(body);

    return ResponseWithIdDto.builder().id(result.getId()).build();
  }

  @Operation(summary = "아티스트 삭제", description = "아티스트 삭제.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(mediaType = "application/json")),

  })
  @DeleteMapping("/artist/delete")
  public ResponseEntity<Boolean> artistDelete(@RequestParam String id) {

    Boolean result = artistService.artistDelete(id);

    return ResponseEntity.ok(result);
  }

  @Operation(summary = "아티스트 수정", description = "아티스트 수정 메서드입니다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json")),

  })
  @PutMapping(path = "/artist/update")
  public ResponseWithIdDto updateArtist(@RequestBody() UpdateArtistDto body) {

    ResponseWithIdDto result = artistService.updateArtist(body);

    return ResponseWithIdDto.builder().id(result.getId()).build();
  }



}
