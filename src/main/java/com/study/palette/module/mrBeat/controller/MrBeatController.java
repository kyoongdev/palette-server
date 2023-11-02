package com.study.palette.module.mrBeat.controller;

import com.study.palette.common.dto.PageDto;
import com.study.palette.common.dto.PaginationDto;
import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.albumArt.dto.info.AlbumArtCreateRequestDto;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.artist.dto.CreateArtistDto;
import com.study.palette.module.mrBeat.dto.CreateMrBeatDto;
import com.study.palette.module.mrBeat.dto.MrBeatResponseDto;
import com.study.palette.module.mrBeat.dto.UpdateMrBeatDto;
import com.study.palette.module.mrBeat.service.MrBeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "mr/beat", description = "mr/beat")
@RequestMapping("/api/mr-beat")
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
    //ResponseEntity -> PaginationDto
    //만들어둔 페이지네이션 객체 사용
    public PaginationDto<MrBeatResponseDto> artistInfo(@ParameterObject PageDto query) {
        PaginationDto<MrBeatResponseDto> artistInfo = mrBeatService.findMrBeat(query.toPageable(Sort.by("createdAt")));

        return artistInfo;

    }

    @Operation(summary = "mr/beat 생성", description = "mr/beat 생성 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(mediaType = "application/json")),

    })
    @PostMapping(path = "/save")
    public ResponseWithIdDto createArtist(@RequestBody() CreateMrBeatDto body) {

        ResponseWithIdDto result = mrBeatService.createMrBeat(body);

        return result;
    }

    @Operation(summary = "mr/beat 수정", description = "mr/beat 수정 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtCreateRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseWithIdDto updateMrBeat(@PathVariable String id, @RequestBody UpdateMrBeatDto body) {

        ResponseWithIdDto result = mrBeatService.updateMrBeat(id, body);

        return result;

    }

    @Operation(summary = "mr/beat 삭제", description = "mr/beat 삭제 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMrBeat(@PathVariable String id) {
        mrBeatService.deleteMrBeat(id);
    }


}
