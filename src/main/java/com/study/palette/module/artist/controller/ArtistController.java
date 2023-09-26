package com.study.palette.module.artist.controller;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.artist.dto.query.FindArtistsQuery;
import com.study.palette.module.artist.service.ArtistService;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "아티스트", description = "아티스트")
@RestController
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    //TODO: Swagger 적용
    @Operation(summary = "아티스트 조회", description = "아티스트 조회 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("/artist")
    //ResponseEntity -> PaginationDto
    //만들어둔 페이지네이션 객체 사용
    public PaginationDto<ArtistResponseDto> artistInfo(Pageable pageable, @ParameterObject FindArtistsQuery query ) {
        PaginationDto<ArtistResponseDto> artistInfo = artistService.artistInfo(pageable);

        return artistInfo;

    }


}
