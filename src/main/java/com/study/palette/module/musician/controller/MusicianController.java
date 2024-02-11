package com.study.palette.module.musician.controller;

import com.study.palette.common.dto.PageDto;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.common.enums.adminSales.ServiceType;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.service.ArtistService;
import com.study.palette.module.mixMastering.service.MixMasteringService;
import com.study.palette.module.mrBeat.service.MrBeatService;
import com.study.palette.module.musician.dto.ApprovingServiceDetailResponseDto;
import com.study.palette.module.musician.dto.CreateMusicianDto;
import com.study.palette.module.musician.dto.MusicianReviewResponseDto;
import com.study.palette.module.musician.dto.MusicianSellingResponseDto;
import com.study.palette.module.musician.dto.ResponsePortfolioDto;
import com.study.palette.module.musician.dto.UpdateMusicianIsSellingDto;
import com.study.palette.module.musician.dto.UpdateMusicianProfileDto;
import com.study.palette.module.musician.dto.query.FindMusicianSellingQuery;
import com.study.palette.module.musician.exception.MusicianErrorCode;
import com.study.palette.module.musician.exception.MusicianException;
import com.study.palette.module.musician.service.MusicianService;
import com.study.palette.module.recording.service.RecordingService;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musician")
@Tag(name = "Musician", description = "뮤지션 API")
@Log4j2
public class MusicianController {

  private final MusicianService musicianService;

  private final MrBeatService mrBeatService;

  private final ArtistService artistService;

  private final RecordingService recordingService;

  private final MixMasteringService mixMasteringService;

  private final AlbumArtService albumArtService;

  @Autowired
  public MusicianController(MusicianService musicianService, MrBeatService mrBeatService,
      ArtistService artistService, RecordingService recordingService,
      MixMasteringService mixMasteringService, AlbumArtService albumArtService) {
    this.musicianService = musicianService;
    this.mrBeatService = mrBeatService;
    this.artistService = artistService;
    this.recordingService = recordingService;
    this.mixMasteringService = mixMasteringService;
    this.albumArtService = albumArtService;

  }

  /**
   * 뮤지션 생성
   */
  @Operation(summary = "뮤지션 생성", description = "뮤지션 생성 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "생성 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('MEMBER')")
  public ResponseWithIdDto createMusician(
      @RequestBody CreateMusicianDto createMusicianDto,
      @Parameter(hidden = true) @GetUserInfo Users users) {

    return musicianService.createMuscian(createMusicianDto, users);
  }

//  @Operation(summary = "뮤지션 수정[관리자]", description = "뮤지션 수정 메소드.")
//  @ApiResponses(value = {
//      @ApiResponse(responseCode = "204", description = "생성 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWithIdDto.class))),
//      @ApiResponse(responseCode = "400", description = "Bad Request")
//  })
//  @PatchMapping("")
//  @ResponseStatus(HttpStatus.NO_CONTENT)
//  public ResponseWithIdDto updateMusician(@PathVariable String id,
//      @RequestBody UpdateMusicianDto updateMusicianDto,
//      @Parameter(hidden = true) @GetUserInfo Users users) {
//
//    return musicianService.updateMusician(id, updateMusicianDto);
//  }

//  @Operation(summary = "뮤지션 삭제[관리자]", description = "뮤지션 삭제 메소드.")
//  @ApiResponses(value = {
//      @ApiResponse(responseCode = "204", description = "삭제 성공"),
//      @ApiResponse(responseCode = "400", description = "Bad Request")
//  })
//  @DeleteMapping("")
//  @ResponseStatus(HttpStatus.NO_CONTENT)
//  void deleteMusician(@PathVariable("id") String id) {
//    musicianService.deleteMusician(id);
//  }

  @Operation(summary = "뮤지션 포트폴리오", description = "뮤지션 포트폴리오 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("portfolio")
  @PreAuthorize("hasRole('MUSICIAN')")
  public ResponsePortfolioDto getPortfolio(@Parameter(hidden = true) @GetUserInfo Users users) {

    ResponsePortfolioDto responsePortfolioDto = musicianService.getPortfolio(users);

    return responsePortfolioDto;
  }

  @Operation(summary = "뮤지션 포트폴리오 소개 및 프로필 수정", description = "뮤지션 포트폴리오 소개 및 프로필 수정 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("portfolio")
  @PreAuthorize("hasRole('MUSICIAN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseWithIdDto updatePortfolioProfile(@RequestBody() UpdateMusicianProfileDto data,
      @Parameter(hidden = true) @GetUserInfo Users users) {

    ResponseWithIdDto result = musicianService.updatePortfolioProfile(data, users);

    return ResponseWithIdDto.builder().id(result.getId()).build();
  }

  @Operation(summary = "뮤지션 판매중인 서비스(뮤지션 포트폴리오)페이지", description = "판매하고 있는 뮤지션 서비스 입니다(뮤지션 포트폴리오)")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtistResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("musician-selling")
  @PreAuthorize("hasRole('MUSICIAN')")
  public PaginationDto<MusicianSellingResponseDto> getMusicianSellingList(
      @ParameterObject PageDto pageDto,
      @Parameter(hidden = true) @GetUserInfo Users users) {

    FindMusicianSellingQuery query = FindMusicianSellingQuery.builder()
        .id(users.getId().toString())
        .build();

    PaginationDto<MusicianSellingResponseDto> result = musicianService.getMusicianSellingList(
        pageDto.toPageable(Sort.by("createdAt")), query);

    return result;
  }

  @Operation(summary = "뮤지션 판매중인 서비스 페이지", description = "판매하고 있는 뮤지션 서비스 입니다")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArtistResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("selling")
  public PaginationDto<MusicianSellingResponseDto> getSellingList(
      @ParameterObject FindMusicianSellingQuery query) {

    if (query.getExcludeTargetId() == null && query.getExcludeTargetId() == null) {
      throw new MusicianException(MusicianErrorCode.MUSICIAN_NOT_EXCLUDEID);
    }

    PaginationDto<MusicianSellingResponseDto> result = musicianService.getMusicianSellingList(
        query.toPageable(Sort.by("createdAt")), query);

    return result;
  }

  @Operation(summary = "뮤지션 검토 중인 서비스", description = "뮤지션 검토 중인 서비스입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MusicianReviewResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("review")
  @PreAuthorize("hasRole('MUSICIAN')")
  public PaginationDto<MusicianReviewResponseDto> getReviewList(@ParameterObject PageDto query,
      @Parameter(hidden = true) @GetUserInfo Users users) {

    PaginationDto<MusicianReviewResponseDto> result = musicianService.getReviewList(
        query.toPageable(Sort.by("createdAt")), query, users);

    return result;
  }

  @Operation(summary = "뮤지션 검토 중인 서비스 상세", description = "뮤지션 검토 중인 서비스 상세입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MusicianReviewResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("approve/{id}/detail")
  @PreAuthorize("hasRole('MUSICIAN')")
  public ApprovingServiceDetailResponseDto approvingServiceDetail(@PathVariable String id,
      @RequestParam int serviceType,
      @Parameter(hidden = true) @GetUserInfo Users users) {

    //TODO : 각 재능마켓 CREATEDTO에 ApprovialType.approval 추가.
    switch (ServiceType.findServiceType(serviceType)) {

      case MIX_MASTERING -> {
        return mixMasteringService.approvingServiceDetail(id);
      }

      case ARTIST -> {

        return artistService.approvingServiceDetail(id);
      }
      case RECORDING -> {
        return recordingService.approvingServiceDetail(id);
      }

      case MR_BEAT -> {
        return mrBeatService.approvingServiceDetail(id);
      }

      case ALBUM_ART -> {
        return albumArtService.approvingServiceDetail(id);
      }

      default -> throw new MusicianException(MusicianErrorCode.MUSICIAN_NOT_EXCLUDEID);
    }

  }

  @Operation(summary = "뮤지션 서비스 판매 상태 변경", description = "뮤지션 서비스 판매 상태 변경 입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "생성 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWithIdDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("isSelling/{id}")
  @PreAuthorize("hasRole('MUSICIAN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateMusicianSellingStatus(@PathVariable String id,
      @RequestBody() UpdateMusicianIsSellingDto updateMusicianIsSellingDto,
      @Parameter(hidden = true) @GetUserInfo Users users) {

    switch (ServiceType.findServiceType(updateMusicianIsSellingDto.getServiceType())) {

      case MIX_MASTERING -> mixMasteringService.updateIsSelling(id, updateMusicianIsSellingDto.isSelling());

      case ARTIST-> artistService.updateIsSelling(id, updateMusicianIsSellingDto.isSelling());

      case RECORDING -> recordingService.updateIsSelling(id, updateMusicianIsSellingDto.isSelling());

      case MR_BEAT -> mrBeatService.updateIsSelling(id, updateMusicianIsSellingDto.isSelling());

      case ALBUM_ART -> albumArtService.updateIsSelling(id, updateMusicianIsSellingDto.isSelling());

    }

  }

}
