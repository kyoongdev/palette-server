package com.study.palette.module.albumArt.controller;

import com.study.palette.common.annotations.CustomRestController;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtDetailResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtUpdateRequestDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtsResponseDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
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

@Tag(name = "앨범아트", description = "앨범아트")
@RequestMapping("/albumArts")
@CustomRestController
public class AlbumArtController {

  private AlbumArtService albumArtService;

  @Autowired
  public AlbumArtController(AlbumArtService albumArtService) {
    this.albumArtService = albumArtService;
  }


  @Operation(summary = "앨범아트 목록 조회", description = "앨범아트 목록 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AlbumArtsResponseDto.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("")
  public PaginationDto<AlbumArtsResponseDto> albumArtInfos(
      @ParameterObject FindAlbumArtQuery query) {
    return albumArtService.getAlbumArts(query,
        query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));
  }

  //앨범아트 상세조회
  @Operation(summary = "앨범아트 상세 조회", description = "앨범아트 상세 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtDetailResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/{id}/detail")
  public AlbumArtDetailResponseDto albumArtInfoDetail(@PathVariable String id) {
    return albumArtService.getAlbumArtWithDto(id);
  }


  //앨범아트 등록
  @Operation(summary = "앨범아트 등록", description = "앨범아트 등록 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtCreateResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('MUSICIAN')")
  public ResponseEntity<AlbumArtCreateResponseDto> createAlbumArt(
      @Valid @RequestBody AlbumArtCreateRequestDto albumArtCreateRequestDto,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    return ResponseEntity.ok(
        albumArtService.createAlbumArt(albumArtCreateRequestDto, users));
  }

  //앨범아트 수정
  @Operation(summary = "앨범아트 수정", description = "앨범아트 수정 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('MUSICIAN')")
  public void updateAlbumArt(@PathVariable String id,
      @RequestBody AlbumArtUpdateRequestDto albumArtUpdateRequestDto,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    albumArtService.updateAlbumArt(id, albumArtUpdateRequestDto, users);
  }

  //구매의뢰하기
  @Operation(summary = "앨범아트 구매의뢰", description = "앨범아트 구매의뢰 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "구매의뢰 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping("/{id}/request")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('MEMBER') or hasRole('MUSICIAN')")
  public void createAlbumArtRequest(@PathVariable String id,
      @GetUserInfo Users users) {
    albumArtService.createAlbumArtRequest(id, users);
  }

  //앨범아트 삭제
  @Operation(summary = "앨범아트 삭제", description = "앨범아트 삭제 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtCreateRequestDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAlbumArt(@PathVariable String id,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    albumArtService.deleteAlbumArt(id, users);
  }
}
