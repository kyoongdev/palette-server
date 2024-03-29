package com.study.palette.module.adminMixMastering.controller;

import com.study.palette.module.mixMastering.dto.MixMasteringDetailDto;
import com.study.palette.module.mixMastering.dto.MixMasteringDto;
import com.study.palette.module.mixMastering.service.MixMasteringService;
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

@Tag(name = "[관리자] 믹스마스터링 관리", description = "[관리자] 믹스마스터링 관리")
@RequestMapping("/admin/mix-mastering")
@RestController
public class AdminMixMasteringController {

  private final MixMasteringService mixMasteringService;

  public AdminMixMasteringController(MixMasteringService mixMasteringService) {
    this.mixMasteringService = mixMasteringService;
  }

  //[관리자] 믹스마스터링 상세조회
  @Operation(summary = "[관리자] 믹스마스터링 상세 조회", description = "[관리자] 믹스마스터링 상세 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MixMasteringDetailDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/{id}/detail")
  @PreAuthorize("hasRole('ADMIN')")
  public MixMasteringDetailDto mixMasteringInfoDetail(@PathVariable String id) {
    return mixMasteringService.getMixMastering(id);
  }

  //mixMastering 판매글 등록/신청 승인 반려 처리
  @Operation(summary = "[관리자] 믹스마스터링 판매글 등록/신청 승인 반려 처리", description = "[관리자] 믹스마스터링 판매글 등록/신청 승인 반려 처리 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("{id}/status")
  @PreAuthorize("hasRole('ADMIN')")
  public void mixMasteringInfoDetail(@PathVariable String id, @RequestBody boolean status) {
    mixMasteringService.updateIsSelling(id, status);
  }

  //[관리자] 믹스마스터링 판매글 수정
  @Operation(summary = "[관리자] 믹스마스터링 판매글 수정", description = "[관리자] 믹스마스터링 판매글 수정 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public void mixMasteringInfoDetail(@PathVariable String id, @RequestBody MixMasteringDto dto, @Parameter(hidden = true) @GetUserInfo Users users) {
    mixMasteringService.updateMixMastering(id, dto, users);
  }

  //[관리자] 믹스마스터링 삭제
  @Operation(summary = "[관리자] 믹스마스터링 삭제", description = "[관리자] 믹스마스터링 삭제 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMixMastering(@PathVariable String id,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    mixMasteringService.deleteMixMastering(id, users);
  }
}

