package com.study.palette.module.adminService.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.adminService.dto.FindAdminServiceQuery;
import com.study.palette.module.adminService.dto.ServiceResponseDto;
import com.study.palette.module.adminService.service.AdminService;
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


  @Operation(summary = "판매글 신청 목록 조회", description = "판매글 등록/수정 신청 목록을 조회합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ServiceResponseDto.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("")
  public PaginationDto<ServiceResponseDto> albumArtInfos(
      @ParameterObject FindAdminServiceQuery query) {
    return adminService.getServices(query, query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));
  }

}
