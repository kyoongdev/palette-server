package com.study.palette.module.recording.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.recording.dto.info.RecordingCreateRequestDto;
import com.study.palette.module.recording.dto.info.RecordingCreateResponseDto;
import com.study.palette.module.recording.dto.info.RecordingDetailResponseDto;
import com.study.palette.module.recording.dto.info.RecordingResponseDto;
import com.study.palette.module.recording.dto.info.RecordingUpdateRequestDto;
import com.study.palette.module.recording.dto.query.FindRecordingQuery;
import com.study.palette.module.recording.service.RecordingService;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

@Tag(name = "레코딩", description = "레코딩")
@RequestMapping("/api/recordings")
@RestController
public class RecordingController {

  private RecordingService recordingService;

  @Autowired
  public RecordingController(RecordingService recordingService) {
    this.recordingService = recordingService;
  }

  //레코딩 목록조회
  @Operation(summary = "레코딩 목록 조회", description = "레코딩 목록 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RecordingResponseDto.class)))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("")
  public PaginationDto<RecordingResponseDto> recordingInfos(
      @ParameterObject FindRecordingQuery query) {
    return recordingService.getRecordings(query,
        query.toPageable(Sort.by(Sort.Direction.DESC, "createdAt")));
  }

  //레코딩 상세조회
  @Operation(summary = "레코딩 상세 조회", description = "레코딩 상세 조회 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecordingDetailResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @GetMapping("/{id}/detail")
  public RecordingDetailResponseDto recordingInfoDetail(@PathVariable String id) {
    return recordingService.getRecordingWithDto(id);
  }


  //레코딩 등록
  @Operation(summary = "레코딩 등록", description = "레코딩 등록 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecordingCreateResponseDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('MUSICIAN')")
  public ResponseEntity<RecordingCreateResponseDto> createRecording(
      @Valid @RequestBody RecordingCreateRequestDto recordingCreateRequestDto,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    return ResponseEntity.ok(
        recordingService.createRecording(recordingCreateRequestDto,
            users));
  }

  //레코딩 수정
  @Operation(summary = "레코딩 수정", description = "레코딩 수정 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize("hasRole('MUSICIAN')")
  public void updateRecording(@PathVariable String id,
      @RequestBody RecordingUpdateRequestDto recordingCreateRequestDto,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    recordingService.updateRecording(id, recordingCreateRequestDto,
        users);
  }

  //레코딩 삭제
  @Operation(summary = "레코딩 삭제", description = "레코딩 삭제 메서드입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecordingCreateRequestDto.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request")
  })
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteRecording(@PathVariable String id,
      @Parameter(hidden = true) @GetUserInfo Users users) {
    recordingService.deleteRecording(id, users);
  }
}
