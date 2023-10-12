package com.study.palette.module.artist.dto.query;


import com.study.palette.common.dto.PageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class FindArtistsQuery extends PageDto {

    @Schema(description = "아티스트 필터", defaultValue = "1")
    private int code;

    @Schema(description = "정렬 조건 인기순 rating, 신규등록순 id, 평점순 rating", defaultValue = "rating")
    private String sort;

}
