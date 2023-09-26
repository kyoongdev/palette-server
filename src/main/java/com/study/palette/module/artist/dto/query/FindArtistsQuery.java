package com.study.palette.module.artist.dto.query;


import com.study.palette.common.dto.PageDto;
import lombok.Data;


@Data
public class FindArtistsQuery extends PageDto {
    private String name;
}
