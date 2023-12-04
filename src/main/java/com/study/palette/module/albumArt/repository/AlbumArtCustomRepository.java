package com.study.palette.module.albumArt.repository;

import com.study.palette.module.albumArt.dto.info.AlbumArtsResponseDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumArtCustomRepository {

  Page<AlbumArtsResponseDto> findAll(FindAlbumArtQuery query, Pageable pageable);
}
