package com.study.palette.module.adminService.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.adminService.dto.FindAdminServiceQuery;
import com.study.palette.module.adminService.dto.ServiceCountResponseDto;
import com.study.palette.module.adminService.dto.ServiceResponseDto;
import com.study.palette.module.adminService.service.AdminService;
import com.study.palette.module.albumArt.dto.info.AlbumArtDetailResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "admin service", description = "관리자 서비스 관리")
@RequestMapping("/admin/service")
@RestController
public class AdminServiceController {

  private final AdminService adminService;

  public AdminServiceController(AdminService adminService) {
    this.adminService = adminService;
  }


  //판매글 목록 전체조회 (parameter 에 따라 등록/신청 또는 등록된글로 나뉩니다)
  @Operation(summary = "판매글 목록 조회", description = "판매글 목록을 조회합니다 (parameter 에 따라 등록/신청 또는 등록된글로 나뉩니다)")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ServiceResponseDto.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("")
  public PaginationDto<ServiceResponseDto> serviceInfos(
      @ParameterObject FindAdminServiceQuery query) {
    return adminService.getServices(query,
        query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));
  }

  //판매글 목록 카운트 (parameter 에 따라 등록/신청 또는 등록된글로 나뉩니다)
  @Operation(summary = "판매글 목록 카운트 조회", description = "판매글 목록의 카운트를 조회합니다 (parameter 에 따라 등록/신청 또는 등록된글로 나뉩니다)")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ServiceCountResponseDto.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/count")
  public ServiceCountResponseDto serviceInfosCount(
      @ParameterObject FindAdminServiceQuery query) {
    return adminService.getServicesCount(query);
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


}
