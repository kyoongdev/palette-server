package com.study.palette.module.albumArt.dto.query;


import com.study.palette.common.dto.PageDto;
import lombok.Data;


@Data
public class FindAlbumArtQuery extends PageDto {
    private String name;
}
