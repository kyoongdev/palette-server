package com.study.palette.module.adminService.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.adminService.dto.FindAdminServiceQuery;
import com.study.palette.module.adminService.dto.ServiceCountResponseDto;
import com.study.palette.module.adminService.dto.ServiceResponseDto;
import com.study.palette.module.adminService.service.AdminService;
import com.study.palette.module.albumArt.dto.info.AlbumArtDetailResponseDto;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.service.ArtistService;
import com.study.palette.module.mixMastering.dto.MixMasteringDto;
import com.study.palette.module.mixMastering.service.MixMasteringService;
import com.study.palette.module.mrBeat.dto.MrBeatResponseDto;
import com.study.palette.module.mrBeat.service.MrBeatService;
import com.study.palette.module.recording.dto.info.RecordingResponseDto;
import com.study.palette.module.recording.service.RecordingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "admin service", description = "관리자 서비스 관리")
@RequestMapping("/admin/service")
@RestController
public class AdminServiceController {

  private final AdminService adminService;
  private final ArtistService artistService;
  private final MixMasteringService mixMasteringService;
  private final RecordingService recordingService;
  private final MrBeatService mrBeatService;
  private final AlbumArtService albumArtService;
  public AdminServiceController(AdminService adminService, ArtistService artistService, MixMasteringService mixMasteringService, RecordingService recordingService, MrBeatService mrBeatService, AlbumArtService albumArtService) {
    this.adminService = adminService;
    this.artistService = artistService;
    this.mixMasteringService = mixMasteringService;
    this.recordingService = recordingService;
    this.mrBeatService = mrBeatService;
    this.albumArtService = albumArtService;
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

  //albumArt 판매글 등록/신청 승인 반려 처리
  @Operation(summary = "앨범아트 판매글 등록/신청 승인 반려 처리", description = "앨범아트 판매글 등록/신청 승인 반려 처리 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtDetailResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("album-art/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public AlbumArtDetailResponseDto albumArtInfoDetail(@PathVariable String id, @RequestBody String status) {
    return albumArtService.updateServiceStatus(id, status);
  }

  //artist 판매글 등록/신청 승인 반려 처리
  @Operation(summary = "아티스트 판매글 등록/신청 승인 반려 처리", description = "아티스트 판매글 등록/신청 승인 반려 처리 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtDetailResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("artist/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ArtistResponseDto artistInfoDetail(@PathVariable String id, @RequestBody String status) {
    return artistService.updateServiceStatus(id, status);
  }

  //mix-mastering 판매글 등록/신청 승인 반려 처리
  @Operation(summary = "믹스마스터링 판매글 등록/신청 승인 반려 처리", description = "믹스마스터링 판매글 등록/신청 승인 반려 처리 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtDetailResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("mix-mastering/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public MixMasteringDto mixMasteringInfoDetail(@PathVariable String id, @RequestBody String status) {
    return mixMasteringService.updateServiceStatus(id, status);
  }

  //recording 판매글 등록/신청 승인 반려 처리
  @Operation(summary = "녹음 판매글 등록/신청 승인 반려 처리", description = "녹음 판매글 등록/신청 승인 반려 처리 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtDetailResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("recording/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public RecordingResponseDto recordingInfoDetail(@PathVariable String id, @RequestBody String status) {
    return recordingService.updateServiceStatus(id, status);
  }

  //mr-beat 판매글 등록/신청 승인 반려 처리
  @Operation(summary = "MR비트 판매글 등록/신청 승인 반려 처리", description = "MR비트 판매글 등록/신청 승인 반려 처리 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "처리 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtDetailResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("mr-beat/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public MrBeatResponseDto mrBeatInfoDetail(@PathVariable String id, @RequestBody String status) {
    return mrBeatService.updateServiceStatus(id, status);
  }
}

