package com.study.palette.module.musician.controller;

import com.study.palette.common.dto.ResponseWithIdDto;
import com.study.palette.module.musician.dto.CreateMusicianDto;
import com.study.palette.module.musician.dto.UpdateMusicianDto;
import com.study.palette.module.musician.service.MusicianService;
import com.study.palette.module.users.annotation.GetUserInfo;
import com.study.palette.module.users.dto.MyInfoResponseDto;
import com.study.palette.module.users.entity.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@Tag(name = "Musician", description = "뮤지션 API")
@Log4j2
public class MusicianController {

    private final MusicianService musicianService;

    @Autowired
    public MusicianController(MusicianService musicianService) {
        this.musicianService = musicianService;
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
    //  @PreAuthorize("hasRole('MEMBER')")
    public ResponseWithIdDto createMusician(
        @RequestBody CreateMusicianDto createMusicianDto,
        @Parameter(hidden = true) @GetUserInfo Users users) {

        return musicianService.createMuscian(createMusicianDto, users);
    }

//    //TODO   파일업로드
//    @Operation(summary = "뮤지션 수정", description = "뮤지션 수정 메소드.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "생성 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWithIdDto.class))),
//            @ApiResponse(responseCode = "400", description = "Bad Request")
//    })
//    @PatchMapping("/musician/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseWithIdDto updateMusician(@PathVariable String id, @RequestBody UpdateMusicianDto updateMusicianDto) {
//
//        return musicianService.updateMusician(id, updateMusicianDto);
//    }
//    @Operation(summary = "뮤지션 삭제", description = "뮤지션 삭제 메소드.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "삭제 성공"),
//            @ApiResponse(responseCode = "400", description = "Bad Request")
//    })
//    @DeleteMapping("/musician/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    void deleteMusician(@PathVariable("id") String id) {
//        musicianService.deleteMusician(id);
//    }

}
