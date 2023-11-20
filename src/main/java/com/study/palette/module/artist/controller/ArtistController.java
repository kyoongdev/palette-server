package com.study.palette.module.artist.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.dto.CreateArtistDto;
import com.study.palette.module.artist.dto.UpdateArtistDto;
import com.study.palette.module.artist.dto.query.FindArtistsQuery;
import com.study.palette.module.artist.service.ArtistService;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "아티스트", description = "아티스트")
@RequestMapping("/artists")
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
  @GetMapping("")
  //ResponseEntity -> PaginationDto
  //만들어둔 페이지네이션 객체 사용
  public PaginationDto<ArtistResponseDto> artistInfo(@ParameterObject FindArtistsQuery query) {
    PaginationDto<ArtistResponseDto> artistInfo = artistService.findArtists(query,
        query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));

    return artistInfo;

  }

  @Operation(summary = "아티스트 생성", description = "아티스트 생성 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(mediaType = "application/json")),

  })
  @PostMapping(path = "")
  @PreAuthorize("hasRole('MUSICIAN')")
  public ResponseWithIdDto createArtist(@RequestBody() CreateArtistDto body,
      @Parameter(hidden = true) @GetUserInfo Users users) {

    ResponseWithIdDto result = artistService.createArtist(body, users);

    return ResponseWithIdDto.builder().id(result.getId()).build();
  }

  @Operation(summary = "아티스트 삭제", description = "아티스트 삭제.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(mediaType = "application/json")),

  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> artistDelete(@PathVariable String id,
      @Parameter(hidden = false) @GetUserInfo Users users) {

    Boolean result = artistService.artistDelete(id);

    return ResponseEntity.ok(result);
  }

  @Operation(summary = "아티스트 수정", description = "아티스트 수정 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json")),

  })
  @PatchMapping(path = "/{id}")
  public ResponseWithIdDto updateArtist(@PathVariable String id,
      @RequestBody() UpdateArtistDto body,
      @Parameter(hidden = true) @GetUserInfo Users users) {

    ResponseWithIdDto result = artistService.updateArtist(id, body, users);

    return ResponseWithIdDto.builder().id(result.getId()).build();
  }

  //구매의뢰하기
  @Operation(summary = "아티스트 구매의뢰", description = "아티스트 구매의뢰 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "구매의뢰 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping("/{id}/request")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('MEMBER') or hasRole('MUSICIAN')")
  public void createArtistRequest(@PathVariable String id,
      @GetUserInfo Users users) throws Exception {
    artistService.createArtistRequest(id, users);
  }


}
