package com.study.palette.module.mrBeat.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.mrBeat.dto.CreateMrBeatDto;
import com.study.palette.module.mrBeat.dto.FindMrBeatsQuery;
import com.study.palette.module.mrBeat.dto.MrBeatDetailResponseDto;
import com.study.palette.module.mrBeat.dto.MrBeatResponseDto;
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
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

@Tag(name = "mr/beat", description = "mr/beat")
@RequestMapping("/mr-beat")
@RestController
public class MrBeatController {


  //TODO 파일업로드 및 필터 별 조회
  private final MrBeatService mrBeatService;

  @Autowired
  public MrBeatController(MrBeatService mrBeatService) {
    this.mrBeatService = mrBeatService;
  }

  @Operation(summary = "mr/beat 조회", description = "mr/beat 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("")
  public PaginationDto<MrBeatResponseDto> mrBeat(@ParameterObject FindMrBeatsQuery query) {

    PaginationDto<MrBeatResponseDto> mrBeat = mrBeatService.findMrBeat(query,
        query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));

    return mrBeat;

  }

  @Operation(summary = "mr/beat 조회", description = "mr/beat 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("{id}/detail")
  public MrBeatDetailResponseDto mrBeatInfoDetail(@PathVariable String id) {

    MrBeatDetailResponseDto artistInfo = mrBeatService.findMrBeatDetail(id);

    return artistInfo;

  }

  @Operation(summary = "mr/beat 생성", description = "mr/beat 생성 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(mediaType = "application/json")),

  })
  @PostMapping("")
  @PreAuthorize("hasRole('MUSICIAN')")
  public ResponseWithIdDto createArtist(@RequestBody() CreateMrBeatDto body,
      @Parameter(hidden = true) @GetUserInfo Users users) {

    ResponseWithIdDto result = mrBeatService.createMrBeat(body, users);

    return result;
  }

  @Operation(summary = "mr/beat 수정", description = "mr/beat 수정 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtCreateRequestDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseWithIdDto updateMrBeat(@PathVariable String id,
      @RequestBody UpdateMrBeatDto body) {

    ResponseWithIdDto result = mrBeatService.updateMrBeat(id, body);

    return result;

  }

  @Operation(summary = "mr/beat 삭제", description = "mr/beat 삭제 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMrBeat(@PathVariable String id) {
    mrBeatService.deleteMrBeat(id);
  }

  @Operation(summary = "mr/beat 구매의뢰", description = "mr/beat 구매의뢰 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "구매의뢰 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping("/{id}/request")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('MEMBER') or hasRole('MUSICIAN')")
  public void createAlbumArtRequest(@PathVariable String id,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    mrBeatService.createMrBeatRequest(id, users);
  }


}
