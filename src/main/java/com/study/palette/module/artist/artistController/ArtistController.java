package com.study.palette.module.artist.artistController;

import com.study.palette.common.dto.PaginationDto;
import com.study.palette.module.artist.artistService.ArtistService;
import com.study.palette.module.artist.dto.ArtistResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    //TODO: Swagger 적용
    @GetMapping("/artist")
    //ResponseEntity -> PaginationDto
    //만들어둔 페이지네이션 객체 사용
    public PaginationDto<ArtistResponseDto> artistInfo(Pageable pageable) {
        PaginationDto<ArtistResponseDto> artistInfo = artistService.artistInfo(pageable);

        return artistInfo;

    }


}
