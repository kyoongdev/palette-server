package com.study.palette.module.adminMrBeat.controller;

import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.mrBeat.dto.MrBeatDetailResponseDto;
import com.study.palette.module.mrBeat.dto.UpdateMrBeatDto;
import com.study.palette.module.mrBeat.service.MrBeatService;
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

@Tag(name = "[관리자] MR Beat 관리", description = "관리자 MR beat 관리")
@RequestMapping("/admin/mr-beat")
@RestController
public class AdminMrBeatController {

  private final MrBeatService mrBeatService;

  public AdminMrBeatController(MrBeatService mrBeatService) {
    this.mrBeatService = mrBeatService;
  }

  @Operation(summary = "mr/beat 상세 조회", description = "mr/beat 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("{id}/detail")
  @PreAuthorize("hasRole('ADMIN')")
  public MrBeatDetailResponseDto mrBeatInfoDetail(@PathVariable String id) {

    MrBeatDetailResponseDto artistInfo = mrBeatService.findMrBeatDetail(id);

    return artistInfo;

  }

  //mrBeat 판매글 등록/신청 승인 반려 처리
  @Operation(summary = "레코딩 판매글 등록/신청 승인 반려 처리", description = "레코딩 판매글 등록/신청 승인 반려 처리 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("{id}/status")
  @PreAuthorize("hasRole('ADMIN')")
  public void mrBeatInfoDetail(@PathVariable String id, @RequestBody boolean status) {
    mrBeatService.updateIsSelling(id, status);
  }

  @Operation(summary = "mr/beat 수정", description = "mr/beat 수정 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtCreateRequestDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseWithIdDto updateMrBeat(@PathVariable String id,
      @Parameter(hidden = true) @GetUserInfo Users users, @RequestBody UpdateMrBeatDto body) {

    ResponseWithIdDto result = mrBeatService.updateMrBeat(id, body, users);

    return result;

  }

  @Operation(summary = "mr/beat 삭제", description = "mr/beat 삭제 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteMrBeat(@PathVariable String id,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    mrBeatService.deleteMrBeat(id, users);
  }

}

