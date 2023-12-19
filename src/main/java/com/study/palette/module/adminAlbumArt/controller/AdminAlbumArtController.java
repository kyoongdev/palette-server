package com.study.palette.module.adminAlbumArt.controller;

import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtDetailResponseDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtUpdateRequestDto;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[관리자] 앨범아트 관리", description = "관리자 앨범아트 관리")
@RequestMapping("/admin/album-arts")
@RestController
public class AdminAlbumArtController {

  private final AlbumArtService albumArtService;

  public AdminAlbumArtController(AlbumArtService albumArtService) {
    this.albumArtService = albumArtService;
  }

  //앨범아트 상세조회
  @Operation(summary = "앨범아트 상세 조회", description = "앨범아트 상세 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtDetailResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/{id}/detail")
  @PreAuthorize("hasRole('ADMIN')")
  public AlbumArtDetailResponseDto albumArtInfoDetail(@PathVariable String id) {
    return albumArtService.getAlbumArtWithDto(id);
  }

  //albumArt 판매글 등록/신청 승인 반려 처리
  @Operation(summary = "앨범아트 판매글 등록/신청 승인 반려 처리", description = "앨범아트 판매글 등록/신청 승인 반려 처리 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("{id}/status")
  @PreAuthorize("hasRole('ADMIN')")
  public void albumArtInfoDetail(@PathVariable String id, @RequestBody boolean status) {
    albumArtService.updateServiceStatus(id, status);
  }

  //앨범아트 판매글 수정
  @Operation(summary = "앨범아트 판매글 수정", description = "앨범아트 판매글 수정 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void albumArtInfoDetail(@PathVariable String id, @RequestBody AlbumArtUpdateRequestDto dto, @Parameter(hidden = true) @GetUserInfo Users users) {
    albumArtService.updateAlbumArt(id, dto, users);
  }

  //앨범아트 삭제
  @Operation(summary = "앨범아트 삭제", description = "앨범아트 삭제 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAlbumArt(@PathVariable String id,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    albumArtService.deleteAlbumArt(id, users);
  }
}

