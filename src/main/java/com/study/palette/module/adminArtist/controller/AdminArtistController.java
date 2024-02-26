package com.study.palette.module.adminArtist.controller;

import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.artist.dto.ArtistDetailResponseDto;
import com.study.palette.module.artist.dto.UpdateArtistDto;
import com.study.palette.module.artist.service.ArtistService;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[관리자] 아티스트 관리", description = "관리자 아티스트 관리")
@RequestMapping("/admin/artists")
@RestController
public class AdminArtistController {

  private final ArtistService artistService;

  public AdminArtistController(ArtistService artistService) {
    this.artistService = artistService;
  }

  @Operation(summary = "아티스트 상세", description = "아티스트 상세 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("{id}/detail")
  @PreAuthorize("hasRole('ADMIN')")
  public ArtistDetailResponseDto artistInfo(@PathVariable String id) {
    return artistService.findArtistsDetail(id);
  }

  //artist 판매글 등록/신청 승인 반려 처리
  @Operation(summary = "아티스트 판매글 등록/신청 승인 반려 처리", description = "아티스트 판매글 등록/신청 승인 반려 처리 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("{id}/status")
  @PreAuthorize("hasRole('ADMIN')")
  public void artistInfoDetail(@PathVariable String id, @RequestBody boolean status) {
    artistService.updateIsSelling(id, status);
  }

  @Operation(summary = "아티스트 수정", description = "아티스트 수정 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "400", description = "Bad Request")})
  @PatchMapping(path = "/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseWithIdDto updateArtist(@PathVariable String id, @RequestBody() UpdateArtistDto body, @Parameter(hidden = true) @GetUserInfo Users users) {
    return ResponseWithIdDto.builder().id(artistService.updateArtist(id, body, users).getId()).build();
  }

  @Operation(summary = "아티스트 삭제", description = "아티스트 삭제.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void artistDelete(@PathVariable String id,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    artistService.artistDelete(id, users);
  }
}

