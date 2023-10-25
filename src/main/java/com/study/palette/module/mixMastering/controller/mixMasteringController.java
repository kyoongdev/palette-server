package com.study.palette.module.mixMastering.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.mixMastering.dto.MixMasteringDetailDto;
import com.study.palette.module.mixMastering.dto.MixMasteringDto;
import com.study.palette.module.mixMastering.dto.query.FindMixMasteringQuery;
import com.study.palette.module.mixMastering.service.MixMasteringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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


  @Operation(summary = "믹스 마스터링 목록 조회", description = "믹스 마스터링 목록 조회 메서드입니다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("")
  public PaginationDto<MixMasteringDto> getMixMasteringList(@ParameterObject FindMixMasteringQuery query) {
    return mixMasteringService.getMixMasterings(query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));
  }

  @Operation(summary = "믹스 마스터링 단일 조회", description = "믹스 마스터링 단일 조회 메서드입니다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("{id}/detail")
  public MixMasteringDetailDto getMixMasteringDetail(@PathVariable String id) {
    return mixMasteringService.getMixMastering(id);
  }


}
