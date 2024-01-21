package com.study.palette.module.adminSales.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.adminSales.dto.AdminSalesCountResponseDto;
import com.study.palette.module.adminSales.dto.AdminSalesResponseDto;
import com.study.palette.module.adminSales.dto.FindAdminSalesQuery;
import com.study.palette.module.adminSales.service.AdminSalesService;
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

@Tag(name = "[관리자] 서비스 관리", description = "관리자 서비스 관리")
@RequestMapping("/admin/service")
@RestController
public class AdminSalesController {

  private final AdminSalesService adminSalesService;

  public AdminSalesController(AdminSalesService adminSalesService) {
    this.adminSalesService = adminSalesService;
  }


  //판매글 목록 전체조회 (parameter 에 따라 등록/신청 또는 등록된글로 나뉩니다)
  @Operation(summary = "판매글 목록 조회", description = "판매글 목록을 조회합니다 (parameter 에 따라 등록/신청 또는 등록된글로 나뉩니다)")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AdminSalesResponseDto.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("")
  public PaginationDto<AdminSalesResponseDto> serviceInfos(
      @ParameterObject FindAdminSalesQuery query) {
    return adminSalesService.getServices(query,
        query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));
  }

  //판매글 목록 카운트 (parameter 에 따라 등록/신청 또는 등록된글로 나뉩니다)
  @Operation(summary = "판매글 목록 카운트 조회", description = "판매글 목록의 카운트를 조회합니다 (parameter 에 따라 등록/신청 또는 등록된글로 나뉩니다)")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/count")
  public AdminSalesCountResponseDto serviceInfosCount(
      boolean isRegistrationCompleted) {
    return adminSalesService.getServicesCount(isRegistrationCompleted);
  }

}

