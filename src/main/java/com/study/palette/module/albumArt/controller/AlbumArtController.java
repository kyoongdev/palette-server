package com.study.palette.module.albumArt.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.albumArt.dto.AlbumArtCreateDto;
import com.study.palette.module.albumArt.dto.AlbumArtDetailResponseDto;
import com.study.palette.module.albumArt.dto.AlbumArtResponseDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import com.study.palette.module.user.GetUserInfo;
import com.study.palette.module.user.dto.MyInfoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "앨범아트", description = "앨범아트")
@RequestMapping("/api/albumArts")
@RestController
public class AlbumArtController {

    private AlbumArtService albumArtService;

    @Autowired
    public AlbumArtController(AlbumArtService albumArtService) {
        this.albumArtService = albumArtService;
    }


    @Operation(summary = "앨범아트 목록 조회", description = "앨범아트 목록 조회 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AlbumArtResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("")
    public PaginationDto<AlbumArtResponseDto> albumArtInfos(@ParameterObject FindAlbumArtQuery query) {
        return albumArtService.getAlbumArts(query.toPageable(null));
    }

    //앨범아트 상세조회
    @Operation(summary = "앨범아트 상세 조회", description = "앨범아트 상세 조회 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtDetailResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("/{id}/detail")
    public AlbumArtResponseDto albumArtInfoDetail(@PathVariable String id) {
        return albumArtService.getAlbumArtWithDto(id);
    }


    //앨범아트 등록
    @Operation(summary = "앨범아트 등록", description = "앨범아트 등록 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtCreateDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumArtDetailResponseDto createAlbumArt(@RequestBody AlbumArtCreateDto albumArtCreateDto, @GetUserInfo MyInfoResponseDto myInfoResponseDto) {
        return albumArtService.createAlbumArt(albumArtCreateDto, myInfoResponseDto);
    }

    //앨범아트 수정
    @Operation(summary = "앨범아트 수정", description = "앨범아트 수정 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtCreateDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbumArt(@PathVariable String id) {

    }

    //앨범아트 삭제
    @Operation(summary = "앨범아트 삭제", description = "앨범아트 삭제 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AlbumArtCreateDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumArt(@PathVariable String id) {

    }
}
