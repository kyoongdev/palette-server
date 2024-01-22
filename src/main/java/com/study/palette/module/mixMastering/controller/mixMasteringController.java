package com.study.palette.module.mixMastering.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.mixMastering.dto.CreateMixMasteringDto;
import com.study.palette.module.mixMastering.dto.MixMasteringDetailDto;
import com.study.palette.module.mixMastering.dto.MixMasteringDto;
import com.study.palette.module.mixMastering.dto.MixMasteringsResponseDto;
import com.study.palette.module.mixMastering.dto.query.FindMixMasteringQuery;
import com.study.palette.module.mixMastering.service.MixMasteringService;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "믹스마스터링", description = "믹스마스터링")
@RestController
@RequestMapping(value = "/mix-mastering")
public class mixMasteringController {

  private MixMasteringService mixMasteringService;

  @Autowired
  public mixMasteringController(MixMasteringService mixMasteringService) {
    this.mixMasteringService = mixMasteringService;
  }


  //믹스마스터링 목록 조회
  @Operation(summary = "믹스 마스터링 목록 조회", description = "믹스 마스터링 목록 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("")
  public PaginationDto<MixMasteringsResponseDto> getMixMasteringList(
      @ParameterObject FindMixMasteringQuery query) {
    return mixMasteringService.getMixMasterings(query, query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));
  }

  //믹스마스터링 단일 조회
  @Operation(summary = "믹스 마스터링 단일 조회", description = "믹스 마스터링 단일 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("{id}/detail")
  public MixMasteringDetailDto getMixMasteringDetail(@PathVariable String id) {
    return mixMasteringService.getMixMastering(id);
  }

  //믹스마스터링 등록
  @Operation(summary = "믹스 마스터링 등록", description = "믹스 마스터링 등록 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('MUSICIAN')")
  public ResponseEntity<MixMasteringDto> createMixMastering(
      @Valid @RequestBody CreateMixMasteringDto createMixMasteringDto,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    return ResponseEntity.ok(
        mixMasteringService.createMixMastering(createMixMasteringDto, users));
  }

  //믹스마스터링 수정
  @Operation(summary = "믹스 마스터링 수정", description = "믹스 마스터링 수정 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('MUSICIAN')")
  public void updateMixMastering(@PathVariable String id, @RequestBody MixMasteringDto mixMasteringDto,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    mixMasteringService.updateMixMastering(id, mixMasteringDto, users);
  }

  //믹스마스터링 삭제
  @Operation(summary = "믹스 마스터링 삭제", description = "믹스 마스터링 삭제 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMixMastering(@PathVariable String id,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    mixMasteringService.deleteMixMastering(id, users);
  }

  //믹스마스터링 구매의뢰
  @Operation(summary = "믹스 마스터링 구매의뢰", description = "믹스 마스터링 구매의뢰 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping("{id}/request")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('MEMBER') or hasRole('MUSICIAN')")
  public void createMixMasteringRequest(@PathVariable String id,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    mixMasteringService.createMixMasteringRequest(id, users);
  }
}
