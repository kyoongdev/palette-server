package com.study.palette.module.albumArt.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.albumArt.dto.AlbumArtResponseDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.albumArt.service.AlbumArtService;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("")
    public PaginationDto<AlbumArtResponseDto> albumArtInfo( @ParameterObject FindAlbumArtQuery query ) {
        PaginationDto<AlbumArtResponseDto> albumArtInfos = albumArtService.findAlbumArtInfos(query.toPageable(null));

        return albumArtInfos;

    }


}
