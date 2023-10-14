package com.study.palette.module.albumArt.dto.query;


import com.study.palette.common.dto.PageDto;
import lombok.Data;


//TODO: 필터 + 정렬 추가
@Data
public class FindAlbumArtQuery extends PageDto {
    private String name;


}
